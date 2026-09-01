package com.enterprisepro.erp.repository;

import com.enterprisepro.erp.entity.JournalEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface JournalEntryRepository extends JpaRepository<JournalEntry, Long> {
    Optional<JournalEntry> findByEntryNumber(String entryNumber);
    List<JournalEntry> findByEntryDateBetween(LocalDate startDate, LocalDate endDate);
    Page<JournalEntry> findByEntryDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);
}
