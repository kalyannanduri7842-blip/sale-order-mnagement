package com.enterprisepro.erp.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "asset_maintenances")
public class AssetMaintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "asset_id", nullable = false)
    private Asset asset;

    @Column(nullable = false, length = 150)
    private String maintenanceTitle;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private LocalDate serviceDate;

    private LocalDate nextServiceDueDate;

    @Column(precision = 12, scale = 2)
    private BigDecimal cost;

    @Column(length = 100)
    private String serviceProvider;

    @Column(length = 30)
    private String status; // SCHEDULED, IN_PROGRESS, COMPLETED

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (serviceDate == null) serviceDate = LocalDate.now();
        if (status == null) status = "COMPLETED";
    }

    public AssetMaintenance() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public String getMaintenanceTitle() {
        return maintenanceTitle;
    }

    public void setMaintenanceTitle(String maintenanceTitle) {
        this.maintenanceTitle = maintenanceTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(LocalDate serviceDate) {
        this.serviceDate = serviceDate;
    }

    public LocalDate getNextServiceDueDate() {
        return nextServiceDueDate;
    }

    public void setNextServiceDueDate(LocalDate nextServiceDueDate) {
        this.nextServiceDueDate = nextServiceDueDate;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(String serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
