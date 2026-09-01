package com.enterprisepro.erp.service;

import com.enterprisepro.erp.dto.ExpenseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExpenseService {
    Page<ExpenseDto> getExpenses(String search, Pageable pageable);
    ExpenseDto getExpenseById(Long id);
    ExpenseDto createExpense(ExpenseDto expenseDto);
    ExpenseDto approveExpense(Long id);
    ExpenseDto rejectExpense(Long id);
    void deleteExpense(Long id);
}
