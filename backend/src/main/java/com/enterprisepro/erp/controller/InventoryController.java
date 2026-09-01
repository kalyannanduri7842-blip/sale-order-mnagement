package com.enterprisepro.erp.controller;

import com.enterprisepro.erp.dto.StockAdjustmentDto;
import com.enterprisepro.erp.dto.StockMovementDto;
import com.enterprisepro.erp.dto.StockTransferDto;
import com.enterprisepro.erp.service.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@Tag(name = "Stock & Inventory Movements", description = "Stock transfers, adjustments, stock card ledger and movement history")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/movements")
    @Operation(summary = "Get paginated stock movement audit trail")
    public ResponseEntity<Page<StockMovementDto>> getStockMovements(
            @PageableDefault(size = 15, sort = "movementDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(inventoryService.getStockMovements(pageable));
    }

    @GetMapping("/movements/product/{productId}")
    @Operation(summary = "Get stock movement ledger for a product")
    public ResponseEntity<List<StockMovementDto>> getProductMovementHistory(@PathVariable Long productId) {
        return ResponseEntity.ok(inventoryService.getProductMovementHistory(productId));
    }

    @PostMapping("/transfers")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'INVENTORY_MANAGER', 'WAREHOUSE_MANAGER')")
    @Operation(summary = "Execute an inter-warehouse stock transfer")
    public ResponseEntity<StockTransferDto> transferStock(@Valid @RequestBody StockTransferDto transferDto) {
        StockTransferDto created = inventoryService.transferStock(transferDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PostMapping("/adjustments")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'INVENTORY_MANAGER')")
    @Operation(summary = "Record a physical stock count adjustment (Addition or Subtraction)")
    public ResponseEntity<StockAdjustmentDto> adjustStock(@Valid @RequestBody StockAdjustmentDto adjustmentDto) {
        StockAdjustmentDto created = inventoryService.adjustStock(adjustmentDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
}
