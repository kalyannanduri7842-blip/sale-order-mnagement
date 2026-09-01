package com.enterprisepro.erp.controller;

import com.enterprisepro.erp.dto.SalesInvoiceDto;
import com.enterprisepro.erp.dto.SalesOrderDto;
import com.enterprisepro.erp.service.SalesService;
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
@RequestMapping("/api/sales")
@Tag(name = "Sales Operations & Orders", description = "Endpoints for creating sales orders, fulfilling deliveries and generating tax invoices")
public class SalesController {

    @Autowired
    private SalesService salesService;

    @GetMapping("/orders")
    @Operation(summary = "Get paginated sales orders")
    public ResponseEntity<Page<SalesOrderDto>> getAllSalesOrders(
            @RequestParam(required = false) String status,
            @PageableDefault(size = 10, sort = "orderDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(salesService.getAllSalesOrders(status, pageable));
    }

    @GetMapping("/orders/{id}")
    @Operation(summary = "Get sales order by ID")
    public ResponseEntity<SalesOrderDto> getSalesOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(salesService.getSalesOrderById(id));
    }

    @PostMapping("/orders")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'SALES_MANAGER')")
    @Operation(summary = "Create a sales order")
    public ResponseEntity<SalesOrderDto> createSalesOrder(@Valid @RequestBody SalesOrderDto salesOrderDto) {
        SalesOrderDto created = salesService.createSalesOrder(salesOrderDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/orders/{id}/deliver")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'WAREHOUSE_MANAGER', 'SALES_MANAGER')")
    @Operation(summary = "Deliver sales order and update customer expenditure")
    public ResponseEntity<SalesOrderDto> deliverSalesOrder(@PathVariable Long id) {
        return ResponseEntity.ok(salesService.deliverSalesOrder(id));
    }

    @PostMapping("/orders/{id}/generate-invoice")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'FINANCE_MANAGER', 'SALES_MANAGER')")
    @Operation(summary = "Generate tax invoice from sales order")
    public ResponseEntity<SalesInvoiceDto> generateSalesInvoice(@PathVariable Long id) {
        return ResponseEntity.ok(salesService.generateSalesInvoice(id));
    }

    @GetMapping("/invoices")
    @Operation(summary = "Get paginated sales invoices")
    public ResponseEntity<Page<SalesInvoiceDto>> getSalesInvoices(
            @RequestParam(required = false) String status,
            @PageableDefault(size = 10, sort = "invoiceDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(salesService.getSalesInvoices(status, pageable));
    }
}
