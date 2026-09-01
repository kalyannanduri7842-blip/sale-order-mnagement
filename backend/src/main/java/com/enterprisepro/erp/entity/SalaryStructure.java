package com.enterprisepro.erp.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "salary_structures")
public class SalaryStructure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", unique = true, nullable = false)
    private Employee employee;

    @Column(precision = 12, scale = 2, nullable = false)
    private BigDecimal basicSalary;

    @Column(precision = 12, scale = 2)
    private BigDecimal houseRentAllowance; // HRA

    @Column(precision = 12, scale = 2)
    private BigDecimal transportAllowance;

    @Column(precision = 12, scale = 2)
    private BigDecimal medicalAllowance;

    @Column(precision = 12, scale = 2)
    private BigDecimal specialAllowance;

    @Column(precision = 12, scale = 2)
    private BigDecimal providentFund; // PF Deduction

    @Column(precision = 12, scale = 2)
    private BigDecimal professionalTax;

    @Column(precision = 12, scale = 2)
    private BigDecimal incomeTax;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (houseRentAllowance == null) houseRentAllowance = BigDecimal.ZERO;
        if (transportAllowance == null) transportAllowance = BigDecimal.ZERO;
        if (medicalAllowance == null) medicalAllowance = BigDecimal.ZERO;
        if (specialAllowance == null) specialAllowance = BigDecimal.ZERO;
        if (providentFund == null) providentFund = BigDecimal.ZERO;
        if (professionalTax == null) professionalTax = BigDecimal.ZERO;
        if (incomeTax == null) incomeTax = BigDecimal.ZERO;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public SalaryStructure() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public BigDecimal getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(BigDecimal basicSalary) {
        this.basicSalary = basicSalary;
    }

    public BigDecimal getHouseRentAllowance() {
        return houseRentAllowance;
    }

    public void setHouseRentAllowance(BigDecimal houseRentAllowance) {
        this.houseRentAllowance = houseRentAllowance;
    }

    public BigDecimal getTransportAllowance() {
        return transportAllowance;
    }

    public void setTransportAllowance(BigDecimal transportAllowance) {
        this.transportAllowance = transportAllowance;
    }

    public BigDecimal getMedicalAllowance() {
        return medicalAllowance;
    }

    public void setMedicalAllowance(BigDecimal medicalAllowance) {
        this.medicalAllowance = medicalAllowance;
    }

    public BigDecimal getSpecialAllowance() {
        return specialAllowance;
    }

    public void setSpecialAllowance(BigDecimal specialAllowance) {
        this.specialAllowance = specialAllowance;
    }

    public BigDecimal getProvidentFund() {
        return providentFund;
    }

    public void setProvidentFund(BigDecimal providentFund) {
        this.providentFund = providentFund;
    }

    public BigDecimal getProfessionalTax() {
        return professionalTax;
    }

    public void setProfessionalTax(BigDecimal professionalTax) {
        this.professionalTax = professionalTax;
    }

    public BigDecimal getIncomeTax() {
        return incomeTax;
    }

    public void setIncomeTax(BigDecimal incomeTax) {
        this.incomeTax = incomeTax;
    }

    public BigDecimal getTotalEarnings() {
        BigDecimal total = basicSalary != null ? basicSalary : BigDecimal.ZERO;
        if (houseRentAllowance != null) total = total.add(houseRentAllowance);
        if (transportAllowance != null) total = total.add(transportAllowance);
        if (medicalAllowance != null) total = total.add(medicalAllowance);
        if (specialAllowance != null) total = total.add(specialAllowance);
        return total;
    }

    public BigDecimal getTotalDeductions() {
        BigDecimal total = BigDecimal.ZERO;
        if (providentFund != null) total = total.add(providentFund);
        if (professionalTax != null) total = total.add(professionalTax);
        if (incomeTax != null) total = total.add(incomeTax);
        return total;
    }

    public BigDecimal getNetSalary() {
        return getTotalEarnings().subtract(getTotalDeductions());
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
