package com.enterprisepro.erp.repository;

import com.enterprisepro.erp.entity.Expense;
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
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByExpenseDateBetween(LocalDate startDate, LocalDate endDate);
    List<Expense> findByCategory(String category);
    
    @Query("SELECT SUM(e.amount) FROM Expense e WHERE e.expenseDate BETWEEN :startDate AND :endDate AND e.status != 'REJECTED'")
    BigDecimal sumExpensesBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    Page<Expense> findByTitleContainingIgnoreCaseOrExpenseNumberContainingIgnoreCaseOrCategoryContainingIgnoreCase(
            String title, String expenseNumber, String category, Pageable pageable);
}
