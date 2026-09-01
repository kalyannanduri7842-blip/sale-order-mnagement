package com.enterprisepro.erp.service.impl;

import com.enterprisepro.erp.dto.AttendanceDto;
import com.enterprisepro.erp.entity.Attendance;
import com.enterprisepro.erp.entity.Employee;
import com.enterprisepro.erp.exception.BadRequestException;
import com.enterprisepro.erp.exception.ResourceNotFoundException;
import com.enterprisepro.erp.repository.AttendanceRepository;
import com.enterprisepro.erp.repository.EmployeeRepository;
import com.enterprisepro.erp.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<AttendanceDto> getAttendanceByDate(LocalDate date, Pageable pageable) {
        if (date == null) date = LocalDate.now();
        return attendanceRepository.findByAttendanceDate(date, pageable).map(this::mapToDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AttendanceDto> getEmployeeAttendanceHistory(Long employeeId, LocalDate startDate, LocalDate endDate) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

        if (startDate == null) startDate = LocalDate.now().minusDays(30);
        if (endDate == null) endDate = LocalDate.now();

        return attendanceRepository.findByEmployeeAndAttendanceDateBetween(employee, startDate, endDate).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AttendanceDto checkIn(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

        LocalDate today = LocalDate.now();
        Optional<Attendance> existing = attendanceRepository.findByEmployeeAndAttendanceDate(employee, today);
        if (existing.isPresent() && existing.get().getCheckInTime() != null) {
            throw new BadRequestException("Employee is already checked in for today at " + existing.get().getCheckInTime());
        }

        Attendance attendance = existing.orElse(new Attendance());
        attendance.setEmployee(employee);
        attendance.setAttendanceDate(today);
        attendance.setCheckInTime(LocalTime.now());

        // Check if late (after 9:30 AM)
        if (attendance.getCheckInTime().isAfter(LocalTime.of(9, 30))) {
            attendance.setStatus("LATE");
        } else {
            attendance.setStatus("PRESENT");
        }

        Attendance saved = attendanceRepository.save(attendance);
        return mapToDto(saved);
    }

    @Override
    @Transactional
    public AttendanceDto checkOut(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

        LocalDate today = LocalDate.now();
        Attendance attendance = attendanceRepository.findByEmployeeAndAttendanceDate(employee, today)
                .orElseThrow(() -> new BadRequestException("No check-in record found for today. Please check in first."));

        LocalTime checkOutTime = LocalTime.now();
        attendance.setCheckOutTime(checkOutTime);

        if (attendance.getCheckInTime() != null) {
            Duration duration = Duration.between(attendance.getCheckInTime(), checkOutTime);
            double hours = duration.toMinutes() / 60.0;
            attendance.setTotalHours(Math.round(hours * 100.0) / 100.0);

            if (hours > 8.0) {
                attendance.setOvertimeHours(Math.round((hours - 8.0) * 100.0) / 100.0);
            } else {
                attendance.setOvertimeHours(0.0);
            }

            if (hours < 4.0 && !"LATE".equals(attendance.getStatus())) {
                attendance.setStatus("HALF_DAY");
            }
        }

        Attendance saved = attendanceRepository.save(attendance);
        return mapToDto(saved);
    }

    @Override
    @Transactional
    public AttendanceDto manualLog(AttendanceDto dto) {
        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", dto.getEmployeeId()));

        Attendance attendance = attendanceRepository.findByEmployeeAndAttendanceDate(employee, dto.getAttendanceDate())
                .orElse(new Attendance());

        attendance.setEmployee(employee);
        attendance.setAttendanceDate(dto.getAttendanceDate());
        attendance.setCheckInTime(dto.getCheckInTime());
        attendance.setCheckOutTime(dto.getCheckOutTime());
        attendance.setStatus(dto.getStatus());
        attendance.setTotalHours(dto.getTotalHours());
        attendance.setOvertimeHours(dto.getOvertimeHours());
        attendance.setNotes(dto.getNotes());

        Attendance saved = attendanceRepository.save(attendance);
        return mapToDto(saved);
    }

    private AttendanceDto mapToDto(Attendance attendance) {
        AttendanceDto dto = new AttendanceDto();
        dto.setId(attendance.getId());
        dto.setEmployeeId(attendance.getEmployee().getId());
        dto.setEmployeeName(attendance.getEmployee().getFullName());
        dto.setEmployeeCode(attendance.getEmployee().getEmployeeId());
        dto.setAttendanceDate(attendance.getAttendanceDate());
        dto.setCheckInTime(attendance.getCheckInTime());
        dto.setCheckOutTime(attendance.getCheckOutTime());
        dto.setTotalHours(attendance.getTotalHours());
        dto.setOvertimeHours(attendance.getOvertimeHours());
        dto.setStatus(attendance.getStatus());
        dto.setNotes(attendance.getNotes());
        return dto;
    }
}
