package com.enterprisepro.erp.controller;

import com.enterprisepro.erp.dto.WarehouseDto;
import com.enterprisepro.erp.service.WarehouseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouses")
@Tag(name = "Warehouse Management", description = "Endpoints for managing multiple warehouses, storage locations and capacities")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @GetMapping
    @Operation(summary = "Get paginated warehouses")
    public ResponseEntity<Page<WarehouseDto>> getAllWarehouses(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(warehouseService.getAllWarehouses(search, pageable));
    }

    @GetMapping("/active")
    @Operation(summary = "Get active warehouses list")
    public ResponseEntity<List<WarehouseDto>> getActiveWarehouses() {
        return ResponseEntity.ok(warehouseService.getActiveWarehouses());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get warehouse by ID")
    public ResponseEntity<WarehouseDto> getWarehouseById(@PathVariable Long id) {
        return ResponseEntity.ok(warehouseService.getWarehouseById(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'WAREHOUSE_MANAGER')")
    @Operation(summary = "Create a new warehouse facility")
    public ResponseEntity<WarehouseDto> createWarehouse(@Valid @RequestBody WarehouseDto warehouseDto) {
        WarehouseDto created = warehouseService.createWarehouse(warehouseDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'WAREHOUSE_MANAGER')")
    @Operation(summary = "Update warehouse facility")
    public ResponseEntity<WarehouseDto> updateWarehouse(@PathVariable Long id, @Valid @RequestBody WarehouseDto warehouseDto) {
        return ResponseEntity.ok(warehouseService.updateWarehouse(id, warehouseDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'WAREHOUSE_MANAGER')")
    @Operation(summary = "Deactivate a warehouse")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable Long id) {
        warehouseService.deleteWarehouse(id);
        return ResponseEntity.noContent().build();
    }
}
