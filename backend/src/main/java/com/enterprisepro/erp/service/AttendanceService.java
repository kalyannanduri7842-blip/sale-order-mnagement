package com.enterprisepro.erp.service;

import com.enterprisepro.erp.dto.AttendanceDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {
    Page<AttendanceDto> getAttendanceByDate(LocalDate date, Pageable pageable);
    List<AttendanceDto> getEmployeeAttendanceHistory(Long employeeId, LocalDate startDate, LocalDate endDate);
    AttendanceDto checkIn(Long employeeId);
    AttendanceDto checkOut(Long employeeId);
    AttendanceDto manualLog(AttendanceDto attendanceDto);
}
