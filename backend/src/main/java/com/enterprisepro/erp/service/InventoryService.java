package com.enterprisepro.erp.service;

import com.enterprisepro.erp.dto.StockAdjustmentDto;
import com.enterprisepro.erp.dto.StockMovementDto;
import com.enterprisepro.erp.dto.StockTransferDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InventoryService {
    Page<StockMovementDto> getStockMovements(Pageable pageable);
    List<StockMovementDto> getProductMovementHistory(Long productId);
    StockTransferDto transferStock(StockTransferDto transferDto);
    StockAdjustmentDto adjustStock(StockAdjustmentDto adjustmentDto);
}
