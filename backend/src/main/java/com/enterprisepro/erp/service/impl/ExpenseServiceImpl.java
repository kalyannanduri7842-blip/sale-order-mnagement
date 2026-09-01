package com.enterprisepro.erp.service.impl;

import com.enterprisepro.erp.dto.ExpenseDto;
import com.enterprisepro.erp.entity.Employee;
import com.enterprisepro.erp.entity.Expense;
import com.enterprisepro.erp.exception.ResourceNotFoundException;
import com.enterprisepro.erp.repository.EmployeeRepository;
import com.enterprisepro.erp.repository.ExpenseRepository;
import com.enterprisepro.erp.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<ExpenseDto> getExpenses(String search, Pageable pageable) {
        if (StringUtils.hasText(search)) {
            return expenseRepository.findByTitleContainingIgnoreCaseOrExpenseNumberContainingIgnoreCaseOrCategoryContainingIgnoreCase(
                    search, search, search, pageable).map(this::mapToDto);
        }
        return expenseRepository.findAll(pageable).map(this::mapToDto);
    }

    @Override
    @Transactional(readOnly = true)
    public ExpenseDto getExpenseById(Long id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense", "id", id));
        return mapToDto(expense);
    }

    @Override
    @Transactional
    public ExpenseDto createExpense(ExpenseDto dto) {
        Expense expense = new Expense();
        expense.setExpenseNumber(StringUtils.hasText(dto.getExpenseNumber()) ? dto.getExpenseNumber() : "EXP-" + (1000 + expenseRepository.count() + 1));
        expense.setTitle(dto.getTitle());
        expense.setCategory(dto.getCategory());
        expense.setAmount(dto.getAmount());
        expense.setExpenseDate(dto.getExpenseDate() != null ? dto.getExpenseDate() : LocalDate.now());
        expense.setPaymentMethod(dto.getPaymentMethod() != null ? dto.getPaymentMethod() : "CASH");
        expense.setReference(dto.getReference());
        expense.setDescription(dto.getDescription());
        expense.setStatus(dto.getStatus() != null ? dto.getStatus() : "APPROVED");
        expense.setReceiptUrl(dto.getReceiptUrl());

        if (dto.getEmployeeId() != null) {
            Employee employee = employeeRepository.findById(dto.getEmployeeId()).orElse(null);
            expense.setEmployee(employee);
        }

        Expense saved = expenseRepository.save(expense);
        return mapToDto(saved);
    }

    @Override
    @Transactional
    public ExpenseDto approveExpense(Long id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense", "id", id));
        expense.setStatus("APPROVED");
        return mapToDto(expenseRepository.save(expense));
    }

    @Override
    @Transactional
    public ExpenseDto rejectExpense(Long id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense", "id", id));
        expense.setStatus("REJECTED");
        return mapToDto(expenseRepository.save(expense));
    }

    @Override
    @Transactional
    public void deleteExpense(Long id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense", "id", id));
        expenseRepository.delete(expense);
    }

    private ExpenseDto mapToDto(Expense expense) {
        ExpenseDto dto = new ExpenseDto();
        dto.setId(expense.getId());
        dto.setExpenseNumber(expense.getExpenseNumber());
        dto.setTitle(expense.getTitle());
        dto.setCategory(expense.getCategory());
        dto.setAmount(expense.getAmount());
        dto.setExpenseDate(expense.getExpenseDate());
        dto.setPaymentMethod(expense.getPaymentMethod());
        dto.setReference(expense.getReference());
        dto.setDescription(expense.getDescription());
        dto.setStatus(expense.getStatus());
        dto.setReceiptUrl(expense.getReceiptUrl());
        if (expense.getEmployee() != null) {
            dto.setEmployeeId(expense.getEmployee().getId());
            dto.setEmployeeName(expense.getEmployee().getFullName());
        }
        return dto;
    }
}
