package com.enterprisepro.erp.service;

import com.enterprisepro.erp.dto.LeaveRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LeaveService {
    Page<LeaveRequestDto> getAllLeaveRequests(String status, Pageable pageable);
    List<LeaveRequestDto> getEmployeeLeaveHistory(Long employeeId);
    LeaveRequestDto applyLeave(LeaveRequestDto leaveRequestDto);
    LeaveRequestDto approveLeave(Long id, Long approverEmployeeId);
    LeaveRequestDto rejectLeave(Long id, Long approverEmployeeId, String reason);
}
