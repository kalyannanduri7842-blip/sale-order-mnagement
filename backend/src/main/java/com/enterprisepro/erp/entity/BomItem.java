package com.enterprisepro.erp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "bom_items")
public class BomItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bom_id", nullable = false)
    @JsonIgnore
    private BillOfMaterials billOfMaterials;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "raw_material_id", nullable = false)
    private Product rawMaterial;

    private int quantityRequired;

    @Column(precision = 12, scale = 2)
    private BigDecimal unitCost;

    @Column(precision = 14, scale = 2)
    private BigDecimal totalCost;

    public BomItem() {}

    public BomItem(Product rawMaterial, int quantityRequired, BigDecimal unitCost) {
        this.rawMaterial = rawMaterial;
        this.quantityRequired = quantityRequired;
        this.unitCost = unitCost;
        this.totalCost = unitCost != null ? unitCost.multiply(BigDecimal.valueOf(quantityRequired)) : BigDecimal.ZERO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BillOfMaterials getBillOfMaterials() {
        return billOfMaterials;
    }

    public void setBillOfMaterials(BillOfMaterials billOfMaterials) {
        this.billOfMaterials = billOfMaterials;
    }

    public Product getRawMaterial() {
        return rawMaterial;
    }

    public void setRawMaterial(Product rawMaterial) {
        this.rawMaterial = rawMaterial;
    }

    public int getQuantityRequired() {
        return quantityRequired;
    }

    public void setQuantityRequired(int quantityRequired) {
        this.quantityRequired = quantityRequired;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }
}
