package com.enterprisepro.erp.repository;

import com.enterprisepro.erp.entity.Income;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {
    List<Income> findByIncomeDateBetween(LocalDate startDate, LocalDate endDate);
    List<Income> findByCategory(String category);

    @Query("SELECT SUM(i.amount) FROM Income i WHERE i.incomeDate BETWEEN :startDate AND :endDate")
    BigDecimal sumIncomeBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    Page<Income> findByTitleContainingIgnoreCaseOrIncomeNumberContainingIgnoreCaseOrCategoryContainingIgnoreCase(
            String title, String incomeNumber, String category, Pageable pageable);
}
