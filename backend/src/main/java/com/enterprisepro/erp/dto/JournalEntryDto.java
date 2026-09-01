package com.enterprisepro.erp.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class JournalEntryDto {

    private Long id;
    private String entryNumber;
    private LocalDate entryDate;
    private String reference;
    private String description;
    private BigDecimal totalDebit;
    private BigDecimal totalCredit;
    private String status;
    private String createdByUsername;
    private List<JournalLineDto> lines;

    public JournalEntryDto() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntryNumber() {
        return entryNumber;
    }

    public void setEntryNumber(String entryNumber) {
        this.entryNumber = entryNumber;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getTotalDebit() {
        return totalDebit;
    }

    public void setTotalDebit(BigDecimal totalDebit) {
        this.totalDebit = totalDebit;
    }

    public BigDecimal getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(BigDecimal totalCredit) {
        this.totalCredit = totalCredit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedByUsername() {
        return createdByUsername;
    }

    public void setCreatedByUsername(String createdByUsername) {
        this.createdByUsername = createdByUsername;
    }

    public List<JournalLineDto> getLines() {
        return lines;
    }

    public void setLines(List<JournalLineDto> lines) {
        this.lines = lines;
    }
}
