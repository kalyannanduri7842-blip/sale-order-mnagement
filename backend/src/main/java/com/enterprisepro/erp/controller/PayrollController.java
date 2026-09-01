package com.enterprisepro.erp.controller;

import com.enterprisepro.erp.dto.PayrollRecordDto;
import com.enterprisepro.erp.dto.PayslipDto;
import com.enterprisepro.erp.service.PayrollService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/payroll")
@Tag(name = "Payroll Management", description = "Endpoints for monthly salary generation, payslips, deductions and disbursement")
public class PayrollController {

    @Autowired
    private PayrollService payrollService;

    @GetMapping
    @Operation(summary = "Get payroll records for a month and year")
    public ResponseEntity<Page<PayrollRecordDto>> getPayrollRecords(
            @RequestParam(defaultValue = "0") int month,
            @RequestParam(defaultValue = "0") int year,
            Pageable pageable) {
        return ResponseEntity.ok(payrollService.getPayrollRecords(month, year, pageable));
    }

    @PostMapping("/generate")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'PAYROLL_MANAGER', 'FINANCE_MANAGER')")
    @Operation(summary = "Generate monthly payroll calculations for all active employees")
    public ResponseEntity<List<PayrollRecordDto>> generateMonthlyPayroll(
            @RequestParam int month,
            @RequestParam int year) {
        return ResponseEntity.ok(payrollService.generateMonthlyPayroll(month, year));
    }

    @PutMapping("/{id}/approve")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'PAYROLL_MANAGER', 'FINANCE_MANAGER')")
    @Operation(summary = "Approve monthly payroll record")
    public ResponseEntity<PayrollRecordDto> approvePayroll(@PathVariable Long id) {
        return ResponseEntity.ok(payrollService.approvePayroll(id));
    }

    @PutMapping("/{id}/process-payment")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'FINANCE_MANAGER', 'ACCOUNTANT')")
    @Operation(summary = "Process salary payment and generate payslip")
    public ResponseEntity<PayrollRecordDto> processPayment(
            @PathVariable Long id,
            @RequestBody(required = false) Map<String, String> payload) {
        String paymentMethod = payload != null ? payload.get("paymentMethod") : "BANK_TRANSFER";
        String reference = payload != null ? payload.get("reference") : null;
        return ResponseEntity.ok(payrollService.processPayment(id, paymentMethod, reference));
    }

    @GetMapping("/payslips/number/{payslipNumber}")
    @Operation(summary = "Get payslip details by payslip number")
    public ResponseEntity<PayslipDto> getPayslipByNumber(@PathVariable String payslipNumber) {
        return ResponseEntity.ok(payrollService.getPayslipByNumber(payslipNumber));
    }

    @GetMapping("/payslips/employee/{employeeId}")
    @Operation(summary = "Get payslip history for employee")
    public ResponseEntity<List<PayslipDto>> getEmployeePayslips(@PathVariable Long employeeId) {
        return ResponseEntity.ok(payrollService.getEmployeePayslips(employeeId));
    }
}
