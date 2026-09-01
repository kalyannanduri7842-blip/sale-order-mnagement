package com.enterprisepro.erp.repository;

import com.enterprisepro.erp.entity.Employee;
import com.enterprisepro.erp.entity.PayrollRecord;
import com.enterprisepro.erp.entity.Payslip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PayslipRepository extends JpaRepository<Payslip, Long> {
    Optional<Payslip> findByPayslipNumber(String payslipNumber);
    Optional<Payslip> findByPayrollRecord(PayrollRecord payrollRecord);
    List<Payslip> findByEmployeeOrderByYearDescMonthDesc(Employee employee);
    Page<Payslip> findByMonthAndYear(int month, int year, Pageable pageable);
}
