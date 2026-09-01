package com.enterprisepro.erp.repository;

import com.enterprisepro.erp.entity.Employee;
import com.enterprisepro.erp.entity.PayrollRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface PayrollRecordRepository extends JpaRepository<PayrollRecord, Long> {
    List<PayrollRecord> findByMonthAndYear(int month, int year);
    Optional<PayrollRecord> findByEmployeeAndMonthAndYear(Employee employee, int month, int year);
    List<PayrollRecord> findByEmployeeOrderByYearDescMonthDesc(Employee employee);
    
    @Query("SELECT SUM(p.netSalary) FROM PayrollRecord p WHERE p.month = :month AND p.year = :year AND p.paymentStatus != 'CANCELLED'")
    BigDecimal sumNetSalaryForMonth(@Param("month") int month, @Param("year") int year);

    Page<PayrollRecord> findByMonthAndYear(int month, int year, Pageable pageable);
}
