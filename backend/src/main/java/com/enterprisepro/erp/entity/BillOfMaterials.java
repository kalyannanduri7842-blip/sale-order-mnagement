package com.enterprisepro.erp.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bill_of_materials")
public class BillOfMaterials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 40)
    private String bomNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "finished_product_id", nullable = false)
    private Product finishedProduct;

    private int batchQuantity = 1;

    @Column(precision = 14, scale = 2)
    private BigDecimal estimatedTotalCost;

    @Column(length = 20)
    private String version = "v1.0";

    private boolean active = true;

    @OneToMany(mappedBy = "billOfMaterials", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BomItem> items = new ArrayList<>();

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public BillOfMaterials() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBomNumber() {
        return bomNumber;
    }

    public void setBomNumber(String bomNumber) {
        this.bomNumber = bomNumber;
    }

    public Product getFinishedProduct() {
        return finishedProduct;
    }

    public void setFinishedProduct(Product finishedProduct) {
        this.finishedProduct = finishedProduct;
    }

    public int getBatchQuantity() {
        return batchQuantity;
    }

    public void setBatchQuantity(int batchQuantity) {
        this.batchQuantity = batchQuantity;
    }

    public BigDecimal getEstimatedTotalCost() {
        return estimatedTotalCost;
    }

    public void setEstimatedTotalCost(BigDecimal estimatedTotalCost) {
        this.estimatedTotalCost = estimatedTotalCost;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<BomItem> getItems() {
        return items;
    }

    public void setItems(List<BomItem> items) {
        this.items = items;
    }

    public void addItem(BomItem item) {
        items.add(item);
        item.setBillOfMaterials(this);
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
