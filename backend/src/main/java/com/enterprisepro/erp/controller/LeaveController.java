package com.enterprisepro.erp.controller;

import com.enterprisepro.erp.dto.LeaveRequestDto;
import com.enterprisepro.erp.service.LeaveService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/leaves")
@Tag(name = "Leave Management", description = "Endpoints for leave applications, balances, approval workflows and history")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @GetMapping
    @Operation(summary = "Get paginated list of leave requests with status filter")
    public ResponseEntity<Page<LeaveRequestDto>> getAllLeaveRequests(
            @RequestParam(required = false) String status,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(leaveService.getAllLeaveRequests(status, pageable));
    }

    @GetMapping("/employee/{employeeId}")
    @Operation(summary = "Get leave application history for specific employee")
    public ResponseEntity<List<LeaveRequestDto>> getEmployeeLeaveHistory(@PathVariable Long employeeId) {
        return ResponseEntity.ok(leaveService.getEmployeeLeaveHistory(employeeId));
    }

    @PostMapping
    @Operation(summary = "Submit a new leave application")
    public ResponseEntity<LeaveRequestDto> applyLeave(@Valid @RequestBody LeaveRequestDto leaveRequestDto) {
        LeaveRequestDto created = leaveService.applyLeave(leaveRequestDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/approve")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'HR_MANAGER', 'HR_EXECUTIVE')")
    @Operation(summary = "Approve a pending leave request")
    public ResponseEntity<LeaveRequestDto> approveLeave(
            @PathVariable Long id,
            @RequestParam(required = false) Long approverEmployeeId) {
        return ResponseEntity.ok(leaveService.approveLeave(id, approverEmployeeId));
    }

    @PutMapping("/{id}/reject")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'HR_MANAGER', 'HR_EXECUTIVE')")
    @Operation(summary = "Reject a pending leave request with reason")
    public ResponseEntity<LeaveRequestDto> rejectLeave(
            @PathVariable Long id,
            @RequestParam(required = false) Long approverEmployeeId,
            @RequestBody(required = false) Map<String, String> payload) {
        String reason = payload != null ? payload.get("reason") : "Not approved";
        return ResponseEntity.ok(leaveService.rejectLeave(id, approverEmployeeId, reason));
    }
}
