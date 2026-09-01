package com.enterprisepro.erp.service.impl;

import com.enterprisepro.erp.dto.PayrollRecordDto;
import com.enterprisepro.erp.dto.PayslipDto;
import com.enterprisepro.erp.entity.Employee;
import com.enterprisepro.erp.entity.PayrollRecord;
import com.enterprisepro.erp.entity.Payslip;
import com.enterprisepro.erp.entity.SalaryStructure;
import com.enterprisepro.erp.exception.BadRequestException;
import com.enterprisepro.erp.exception.ResourceNotFoundException;
import com.enterprisepro.erp.repository.EmployeeRepository;
import com.enterprisepro.erp.repository.PayrollRecordRepository;
import com.enterprisepro.erp.repository.PayslipRepository;
import com.enterprisepro.erp.repository.SalaryStructureRepository;
import com.enterprisepro.erp.service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PayrollServiceImpl implements PayrollService {

    @Autowired
    private PayrollRecordRepository payrollRecordRepository;

    @Autowired
    private PayslipRepository payslipRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SalaryStructureRepository salaryStructureRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<PayrollRecordDto> getPayrollRecords(int month, int year, Pageable pageable) {
        if (month == 0) month = LocalDate.now().getMonthValue();
        if (year == 0) year = LocalDate.now().getYear();
        return payrollRecordRepository.findByMonthAndYear(month, year, pageable).map(this::mapRecordToDto);
    }

    @Override
    @Transactional
    public List<PayrollRecordDto> generateMonthlyPayroll(int month, int year) {
        List<Employee> activeEmployees = employeeRepository.findByEmploymentStatus("ACTIVE");
        List<PayrollRecordDto> results = new ArrayList<>();

        for (Employee emp : activeEmployees) {
            Optional<PayrollRecord> existing = payrollRecordRepository.findByEmployeeAndMonthAndYear(emp, month, year);
            PayrollRecord record = existing.orElse(new PayrollRecord());
            record.setEmployee(emp);
            record.setMonth(month);
            record.setYear(year);
            record.setTotalWorkingDays(22);
            record.setPresentDays(20);
            record.setPaidLeaveDays(2);
            record.setUnpaidLeaveDays(0);

            BigDecimal basic = emp.getSalary() != null ? emp.getSalary() : BigDecimal.valueOf(5000);
            BigDecimal hra = basic.multiply(BigDecimal.valueOf(0.40));
            BigDecimal transport = BigDecimal.valueOf(200);
            BigDecimal allowances = hra.add(transport);
            BigDecimal gross = basic.add(allowances);
            BigDecimal pf = basic.multiply(BigDecimal.valueOf(0.12));
            BigDecimal tax = gross.multiply(BigDecimal.valueOf(0.08));
            BigDecimal deductions = pf.add(tax);
            BigDecimal net = gross.subtract(deductions);

            record.setBasicSalary(basic);
            record.setAllowancesTotal(allowances);
            record.setOvertimePay(BigDecimal.ZERO);
            record.setBonuses(BigDecimal.ZERO);
            record.setGrossSalary(gross);
            record.setDeductionsTotal(deductions);
            record.setTaxDeduction(tax);
            record.setNetSalary(net);
            record.setPaymentStatus("PENDING");

            PayrollRecord saved = payrollRecordRepository.save(record);
            results.add(mapRecordToDto(saved));
        }

        return results;
    }

    @Override
    @Transactional
    public PayrollRecordDto approvePayroll(Long payrollId) {
        PayrollRecord record = payrollRecordRepository.findById(payrollId)
                .orElseThrow(() -> new ResourceNotFoundException("PayrollRecord", "id", payrollId));

        record.setPaymentStatus("APPROVED");
        PayrollRecord updated = payrollRecordRepository.save(record);
        return mapRecordToDto(updated);
    }

    @Override
    @Transactional
    public PayrollRecordDto processPayment(Long payrollId, String paymentMethod, String reference) {
        PayrollRecord record = payrollRecordRepository.findById(payrollId)
                .orElseThrow(() -> new ResourceNotFoundException("PayrollRecord", "id", payrollId));

        record.setPaymentStatus("PAID");
        record.setPaymentDate(LocalDate.now());
        record.setPaymentMethod(paymentMethod != null ? paymentMethod : "BANK_TRANSFER");
        record.setTransactionReference(reference != null ? reference : "TXN-" + System.currentTimeMillis());

        PayrollRecord updated = payrollRecordRepository.save(record);

        // Auto-generate payslip
        generatePayslip(record.getId());

        return mapRecordToDto(updated);
    }

    @Override
    @Transactional
    public PayslipDto generatePayslip(Long payrollId) {
        PayrollRecord record = payrollRecordRepository.findById(payrollId)
                .orElseThrow(() -> new ResourceNotFoundException("PayrollRecord", "id", payrollId));

        Optional<Payslip> existing = payslipRepository.findByPayrollRecord(record);
        Payslip payslip = existing.orElse(new Payslip());

        if (payslip.getPayslipNumber() == null) {
            payslip.setPayslipNumber("PS-" + record.getYear() + "-" + String.format("%02d", record.getMonth()) + "-" + record.getEmployee().getEmployeeId());
        }
        payslip.setPayrollRecord(record);
        payslip.setEmployee(record.getEmployee());
        payslip.setMonth(record.getMonth());
        payslip.setYear(record.getYear());
        payslip.setGeneratedDate(LocalDate.now());
        payslip.setBasicPay(record.getBasicSalary());
        payslip.setTotalAllowances(record.getAllowancesTotal());
        payslip.setTotalDeductions(record.getDeductionsTotal());
        payslip.setNetPayable(record.getNetSalary());

        Payslip saved = payslipRepository.save(payslip);
        return mapPayslipToDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public PayslipDto getPayslipByNumber(String payslipNumber) {
        Payslip payslip = payslipRepository.findByPayslipNumber(payslipNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Payslip", "number", payslipNumber));
        return mapPayslipToDto(payslip);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PayslipDto> getEmployeePayslips(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

        return payslipRepository.findByEmployeeOrderByYearDescMonthDesc(employee).stream()
                .map(this::mapPayslipToDto)
                .collect(Collectors.toList());
    }

    private PayrollRecordDto mapRecordToDto(PayrollRecord record) {
        PayrollRecordDto dto = new PayrollRecordDto();
        dto.setId(record.getId());
        dto.setEmployeeId(record.getEmployee().getId());
        dto.setEmployeeName(record.getEmployee().getFullName());
        dto.setEmployeeCode(record.getEmployee().getEmployeeId());
        if (record.getEmployee().getDepartment() != null) {
            dto.setDepartmentName(record.getEmployee().getDepartment().getName());
        }
        dto.setMonth(record.getMonth());
        dto.setYear(record.getYear());
        dto.setTotalWorkingDays(record.getTotalWorkingDays());
        dto.setPresentDays(record.getPresentDays());
        dto.setPaidLeaveDays(record.getPaidLeaveDays());
        dto.setUnpaidLeaveDays(record.getUnpaidLeaveDays());
        dto.setBasicSalary(record.getBasicSalary());
        dto.setAllowancesTotal(record.getAllowancesTotal());
        dto.setOvertimePay(record.getOvertimePay());
        dto.setBonuses(record.getBonuses());
        dto.setGrossSalary(record.getGrossSalary());
        dto.setDeductionsTotal(record.getDeductionsTotal());
        dto.setTaxDeduction(record.getTaxDeduction());
        dto.setNetSalary(record.getNetSalary());
        dto.setPaymentStatus(record.getPaymentStatus());
        dto.setPaymentDate(record.getPaymentDate());
        dto.setPaymentMethod(record.getPaymentMethod());
        dto.setTransactionReference(record.getTransactionReference());
        return dto;
    }

    private PayslipDto mapPayslipToDto(Payslip payslip) {
        PayslipDto dto = new PayslipDto();
        dto.setId(payslip.getId());
        dto.setPayslipNumber(payslip.getPayslipNumber());
        dto.setPayrollRecordId(payslip.getPayrollRecord().getId());
        dto.setEmployeeId(payslip.getEmployee().getId());
        dto.setEmployeeName(payslip.getEmployee().getFullName());
        dto.setEmployeeCode(payslip.getEmployee().getEmployeeId());
        if (payslip.getEmployee().getDesignation() != null) {
            dto.setDesignationTitle(payslip.getEmployee().getDesignation().getTitle());
        }
        if (payslip.getEmployee().getDepartment() != null) {
            dto.setDepartmentName(payslip.getEmployee().getDepartment().getName());
        }
        dto.setMonth(payslip.getMonth());
        dto.setYear(payslip.getYear());
        dto.setGeneratedDate(payslip.getGeneratedDate());
        dto.setBasicPay(payslip.getBasicPay());
        dto.setTotalAllowances(payslip.getTotalAllowances());
        dto.setTotalDeductions(payslip.getTotalDeductions());
        dto.setNetPayable(payslip.getNetPayable());
        dto.setPdfUrl(payslip.getPdfUrl());
        return dto;
    }
}
