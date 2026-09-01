package com.enterprisepro.erp.controller;

import com.enterprisepro.erp.dto.AccountDto;
import com.enterprisepro.erp.dto.IncomeDto;
import com.enterprisepro.erp.dto.JournalEntryDto;
import com.enterprisepro.erp.service.FinanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/finance")
@Tag(name = "Finance & Accounting", description = "Chart of Accounts, double-entry journal vouchers, general ledger, income, trial balance and financial statements")
public class FinanceController {

    @Autowired
    private FinanceService financeService;

    // Accounts
    @GetMapping("/accounts")
    @Operation(summary = "Get full Chart of Accounts hierarchy")
    public ResponseEntity<List<AccountDto>> getChartOfAccounts() {
        return ResponseEntity.ok(financeService.getChartOfAccounts());
    }

    @PostMapping("/accounts")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'FINANCE_MANAGER', 'ACCOUNTANT')")
    @Operation(summary = "Create an account in Chart of Accounts")
    public ResponseEntity<AccountDto> createAccount(@Valid @RequestBody AccountDto accountDto) {
        AccountDto created = financeService.createAccount(accountDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/accounts/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'FINANCE_MANAGER', 'ACCOUNTANT')")
    @Operation(summary = "Update an account")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable Long id, @Valid @RequestBody AccountDto accountDto) {
        return ResponseEntity.ok(financeService.updateAccount(id, accountDto));
    }

    // Journals
    @GetMapping("/journal-entries")
    @Operation(summary = "Get paginated journal entries with date filter")
    public ResponseEntity<Page<JournalEntryDto>> getJournalEntries(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @PageableDefault(size = 10, sort = "entryDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(financeService.getJournalEntries(startDate, endDate, pageable));
    }

    @PostMapping("/journal-entries")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'FINANCE_MANAGER', 'ACCOUNTANT')")
    @Operation(summary = "Create and post a double-entry journal voucher")
    public ResponseEntity<JournalEntryDto> createJournalEntry(@Valid @RequestBody JournalEntryDto journalEntryDto) {
        JournalEntryDto created = financeService.createJournalEntry(journalEntryDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // Income
    @GetMapping("/income")
    @Operation(summary = "Get income records")
    public ResponseEntity<Page<IncomeDto>> getIncomeRecords(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(financeService.getIncomeRecords(search, pageable));
    }

    @PostMapping("/income")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'FINANCE_MANAGER', 'ACCOUNTANT')")
    @Operation(summary = "Record income / revenue transaction")
    public ResponseEntity<IncomeDto> recordIncome(@Valid @RequestBody IncomeDto incomeDto) {
        IncomeDto created = financeService.recordIncome(incomeDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // Statements
    @GetMapping("/reports/trial-balance")
    @Operation(summary = "Generate Real-Time Trial Balance")
    public ResponseEntity<Map<String, Object>> getTrialBalance() {
        return ResponseEntity.ok(financeService.getTrialBalance());
    }

    @GetMapping("/reports/profit-loss")
    @Operation(summary = "Generate Profit and Loss Statement")
    public ResponseEntity<Map<String, Object>> getProfitAndLossStatement(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(financeService.getProfitAndLossStatement(startDate, endDate));
    }

    @GetMapping("/reports/balance-sheet")
    @Operation(summary = "Generate Balance Sheet")
    public ResponseEntity<Map<String, Object>> getBalanceSheet() {
        return ResponseEntity.ok(financeService.getBalanceSheet());
    }
}
