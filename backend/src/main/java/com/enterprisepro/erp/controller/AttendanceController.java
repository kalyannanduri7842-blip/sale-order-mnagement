package com.enterprisepro.erp.controller;

import com.enterprisepro.erp.dto.AttendanceDto;
import com.enterprisepro.erp.service.AttendanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@Tag(name = "Attendance Management", description = "Endpoints for employee check-in/out, attendance records, overtime calculation and reports")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping
    @Operation(summary = "Get daily attendance page for a given date")
    public ResponseEntity<Page<AttendanceDto>> getAttendanceByDate(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            Pageable pageable) {
        return ResponseEntity.ok(attendanceService.getAttendanceByDate(date, pageable));
    }

    @GetMapping("/employee/{employeeId}")
    @Operation(summary = "Get attendance history for a specific employee")
    public ResponseEntity<List<AttendanceDto>> getEmployeeAttendanceHistory(
            @PathVariable Long employeeId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(attendanceService.getEmployeeAttendanceHistory(employeeId, startDate, endDate));
    }

    @PostMapping("/check-in/{employeeId}")
    @Operation(summary = "Record check-in time for employee")
    public ResponseEntity<AttendanceDto> checkIn(@PathVariable Long employeeId) {
        return ResponseEntity.ok(attendanceService.checkIn(employeeId));
    }

    @PostMapping("/check-out/{employeeId}")
    @Operation(summary = "Record check-out time and compute total/overtime hours")
    public ResponseEntity<AttendanceDto> checkOut(@PathVariable Long employeeId) {
        return ResponseEntity.ok(attendanceService.checkOut(employeeId));
    }

    @PostMapping("/manual")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'HR_MANAGER')")
    @Operation(summary = "Manually record or adjust attendance")
    public ResponseEntity<AttendanceDto> manualLog(@Valid @RequestBody AttendanceDto attendanceDto) {
        return ResponseEntity.ok(attendanceService.manualLog(attendanceDto));
    }
}
