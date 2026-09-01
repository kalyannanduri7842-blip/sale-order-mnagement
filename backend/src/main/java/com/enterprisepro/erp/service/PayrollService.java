package com.enterprisepro.erp.service;

import com.enterprisepro.erp.dto.PayrollRecordDto;
import com.enterprisepro.erp.dto.PayslipDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PayrollService {
    Page<PayrollRecordDto> getPayrollRecords(int month, int year, Pageable pageable);
    List<PayrollRecordDto> generateMonthlyPayroll(int month, int year);
    PayrollRecordDto approvePayroll(Long payrollId);
    PayrollRecordDto processPayment(Long payrollId, String paymentMethod, String reference);
    PayslipDto generatePayslip(Long payrollId);
    PayslipDto getPayslipByNumber(String payslipNumber);
    List<PayslipDto> getEmployeePayslips(Long employeeId);
}
