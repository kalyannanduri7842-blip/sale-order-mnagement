package com.enterprisepro.erp.service.impl;

import com.enterprisepro.erp.dto.LeaveRequestDto;
import com.enterprisepro.erp.entity.Employee;
import com.enterprisepro.erp.entity.LeaveRequest;
import com.enterprisepro.erp.exception.BadRequestException;
import com.enterprisepro.erp.exception.ResourceNotFoundException;
import com.enterprisepro.erp.repository.EmployeeRepository;
import com.enterprisepro.erp.repository.LeaveRequestRepository;
import com.enterprisepro.erp.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<LeaveRequestDto> getAllLeaveRequests(String status, Pageable pageable) {
        if (StringUtils.hasText(status)) {
            return leaveRequestRepository.findByStatus(status, pageable).map(this::mapToDto);
        }
        return leaveRequestRepository.findAll(pageable).map(this::mapToDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LeaveRequestDto> getEmployeeLeaveHistory(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

        return leaveRequestRepository.findByEmployeeOrderByCreatedAtDesc(employee).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public LeaveRequestDto applyLeave(LeaveRequestDto dto) {
        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", dto.getEmployeeId()));

        if (dto.getEndDate().isBefore(dto.getStartDate())) {
            throw new BadRequestException("End date cannot be before start date");
        }

        int totalDays = (int) ChronoUnit.DAYS.between(dto.getStartDate(), dto.getEndDate()) + 1;

        LeaveRequest leave = new LeaveRequest();
        leave.setEmployee(employee);
        leave.setLeaveType(dto.getLeaveType());
        leave.setStartDate(dto.getStartDate());
        leave.setEndDate(dto.getEndDate());
        leave.setTotalDays(totalDays);
        leave.setReason(dto.getReason());
        leave.setStatus("PENDING");

        LeaveRequest saved = leaveRequestRepository.save(leave);
        return mapToDto(saved);
    }

    @Override
    @Transactional
    public LeaveRequestDto approveLeave(Long id, Long approverEmployeeId) {
        LeaveRequest leave = leaveRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LeaveRequest", "id", id));

        if (!"PENDING".equals(leave.getStatus())) {
            throw new BadRequestException("Leave request is already " + leave.getStatus());
        }

        Employee approver = null;
        if (approverEmployeeId != null) {
            approver = employeeRepository.findById(approverEmployeeId).orElse(null);
        }

        leave.setStatus("APPROVED");
        leave.setApprovedBy(approver);
        leave.setActionDate(LocalDateTime.now());

        LeaveRequest updated = leaveRequestRepository.save(leave);
        return mapToDto(updated);
    }

    @Override
    @Transactional
    public LeaveRequestDto rejectLeave(Long id, Long approverEmployeeId, String reason) {
        LeaveRequest leave = leaveRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LeaveRequest", "id", id));

        if (!"PENDING".equals(leave.getStatus())) {
            throw new BadRequestException("Leave request is already " + leave.getStatus());
        }

        Employee approver = null;
        if (approverEmployeeId != null) {
            approver = employeeRepository.findById(approverEmployeeId).orElse(null);
        }

        leave.setStatus("REJECTED");
        leave.setApprovedBy(approver);
        leave.setActionDate(LocalDateTime.now());
        leave.setRejectionReason(reason);

        LeaveRequest updated = leaveRequestRepository.save(leave);
        return mapToDto(updated);
    }

    private LeaveRequestDto mapToDto(LeaveRequest leave) {
        LeaveRequestDto dto = new LeaveRequestDto();
        dto.setId(leave.getId());
        dto.setEmployeeId(leave.getEmployee().getId());
        dto.setEmployeeName(leave.getEmployee().getFullName());
        dto.setEmployeeCode(leave.getEmployee().getEmployeeId());
        if (leave.getEmployee().getDepartment() != null) {
            dto.setDepartmentName(leave.getEmployee().getDepartment().getName());
        }
        dto.setLeaveType(leave.getLeaveType());
        dto.setStartDate(leave.getStartDate());
        dto.setEndDate(leave.getEndDate());
        dto.setTotalDays(leave.getTotalDays());
        dto.setReason(leave.getReason());
        dto.setStatus(leave.getStatus());
        if (leave.getApprovedBy() != null) {
            dto.setApprovedByName(leave.getApprovedBy().getFullName());
        }
        dto.setActionDate(leave.getActionDate());
        dto.setRejectionReason(leave.getRejectionReason());
        dto.setCreatedAt(leave.getCreatedAt());
        return dto;
    }
}
