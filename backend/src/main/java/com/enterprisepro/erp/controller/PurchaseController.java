package com.enterprisepro.erp.controller;

import com.enterprisepro.erp.dto.PurchaseOrderDto;
import com.enterprisepro.erp.service.PurchaseService;
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

@RestController
@RequestMapping("/api/purchases")
@Tag(name = "Procurement & Purchase Operations", description = "Purchase orders, goods receipt notes (GRN), invoice matching and vendor orders")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/orders")
    @Operation(summary = "Get paginated purchase orders")
    public ResponseEntity<Page<PurchaseOrderDto>> getAllPurchaseOrders(
            @RequestParam(required = false) String status,
            @PageableDefault(size = 10, sort = "orderDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(purchaseService.getAllPurchaseOrders(status, pageable));
    }

    @GetMapping("/orders/{id}")
    @Operation(summary = "Get purchase order by ID")
    public ResponseEntity<PurchaseOrderDto> getPurchaseOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(purchaseService.getPurchaseOrderById(id));
    }

    @PostMapping("/orders")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'PURCHASE_MANAGER', 'PROCUREMENT_MANAGER')")
    @Operation(summary = "Create and issue a purchase order")
    public ResponseEntity<PurchaseOrderDto> createPurchaseOrder(@Valid @RequestBody PurchaseOrderDto poDto) {
        PurchaseOrderDto created = purchaseService.createPurchaseOrder(poDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/orders/{id}/receive-goods")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'WAREHOUSE_MANAGER', 'INVENTORY_MANAGER')")
    @Operation(summary = "Receive goods, update stock balances and log movement")
    public ResponseEntity<PurchaseOrderDto> receiveGoods(@PathVariable Long id) {
        return ResponseEntity.ok(purchaseService.receiveGoods(id));
    }

    @PutMapping("/orders/{id}/cancel")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'PURCHASE_MANAGER')")
    @Operation(summary = "Cancel a purchase order")
    public ResponseEntity<PurchaseOrderDto> cancelPurchaseOrder(@PathVariable Long id) {
        return ResponseEntity.ok(purchaseService.cancelPurchaseOrder(id));
    }
}
