package com.enterprisepro.erp.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

public class BillOfMaterialsDto {

    private Long id;
    private String bomNumber;

    @NotNull(message = "Finished product is required")
    private Long finishedProductId;
    private String finishedProductName;
    private String finishedProductSku;

    private int batchQuantity = 1;
    private BigDecimal estimatedTotalCost;
    private String version;
    private boolean active = true;
    private List<BomItemDto> items;

    public BillOfMaterialsDto() {}

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

    public Long getFinishedProductId() {
        return finishedProductId;
    }

    public void setFinishedProductId(Long finishedProductId) {
        this.finishedProductId = finishedProductId;
    }

    public String getFinishedProductName() {
        return finishedProductName;
    }

    public void setFinishedProductName(String finishedProductName) {
        this.finishedProductName = finishedProductName;
    }

    public String getFinishedProductSku() {
        return finishedProductSku;
    }

    public void setFinishedProductSku(String finishedProductSku) {
        this.finishedProductSku = finishedProductSku;
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

    public List<BomItemDto> getItems() {
        return items;
    }

    public void setItems(List<BomItemDto> items) {
        this.items = items;
    }
}
