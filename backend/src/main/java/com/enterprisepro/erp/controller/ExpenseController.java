package com.enterprisepro.erp.controller;

import com.enterprisepro.erp.dto.ExpenseDto;
import com.enterprisepro.erp.service.ExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/expenses")
@Tag(name = "Expense Management", description = "Endpoints for logging, categorizing and approving operational expenditures")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping
    @Operation(summary = "Get paginated expenses with search and filters")
    public ResponseEntity<Page<ExpenseDto>> getExpenses(
            @RequestParam(required = false) String search,
            @PageableDefault(size = 10, sort = "expenseDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(expenseService.getExpenses(search, pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get expense by ID")
    public ResponseEntity<ExpenseDto> getExpenseById(@PathVariable Long id) {
        return ResponseEntity.ok(expenseService.getExpenseById(id));
    }

    @PostMapping
    @Operation(summary = "Create an expense record")
    public ResponseEntity<ExpenseDto> createExpense(@Valid @RequestBody ExpenseDto expenseDto) {
        ExpenseDto created = expenseService.createExpense(expenseDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/approve")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'FINANCE_MANAGER')")
    @Operation(summary = "Approve an expense")
    public ResponseEntity<ExpenseDto> approveExpense(@PathVariable Long id) {
        return ResponseEntity.ok(expenseService.approveExpense(id));
    }

    @PutMapping("/{id}/reject")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'FINANCE_MANAGER')")
    @Operation(summary = "Reject an expense")
    public ResponseEntity<ExpenseDto> rejectExpense(@PathVariable Long id) {
        return ResponseEntity.ok(expenseService.rejectExpense(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'FINANCE_MANAGER')")
    @Operation(summary = "Delete an expense")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }
}
