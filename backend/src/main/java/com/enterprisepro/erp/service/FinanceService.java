package com.enterprisepro.erp.service;

import com.enterprisepro.erp.dto.AccountDto;
import com.enterprisepro.erp.dto.IncomeDto;
import com.enterprisepro.erp.dto.JournalEntryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface FinanceService {
    List<AccountDto> getChartOfAccounts();
    AccountDto createAccount(AccountDto accountDto);
    AccountDto updateAccount(Long id, AccountDto accountDto);

    Page<JournalEntryDto> getJournalEntries(LocalDate startDate, LocalDate endDate, Pageable pageable);
    JournalEntryDto createJournalEntry(JournalEntryDto journalEntryDto);

    Page<IncomeDto> getIncomeRecords(String search, Pageable pageable);
    IncomeDto recordIncome(IncomeDto incomeDto);

    Map<String, Object> getTrialBalance();
    Map<String, Object> getProfitAndLossStatement(LocalDate startDate, LocalDate endDate);
    Map<String, Object> getBalanceSheet();
}
