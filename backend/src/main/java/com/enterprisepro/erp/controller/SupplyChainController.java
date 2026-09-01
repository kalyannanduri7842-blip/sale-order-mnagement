package com.enterprisepro.erp.controller;

import com.enterprisepro.erp.dto.ShipmentDto;
import com.enterprisepro.erp.service.SupplyChainService;
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

import java.util.Map;

@RestController
@RequestMapping("/api/supply-chain")
@Tag(name = "Supply Chain & Logistics", description = "Endpoints for freight shipments, tracking codes, carrier logistics and proof of delivery")
public class SupplyChainController {

    @Autowired
    private SupplyChainService supplyChainService;

    @GetMapping("/shipments")
    @Operation(summary = "Get paginated shipments with status filter")
    public ResponseEntity<Page<ShipmentDto>> getAllShipments(
            @RequestParam(required = false) String status,
            Pageable pageable) {
        return ResponseEntity.ok(supplyChainService.getAllShipments(status, pageable));
    }

    @GetMapping("/shipments/{id}")
    @Operation(summary = "Get shipment details by ID")
    public ResponseEntity<ShipmentDto> getShipmentById(@PathVariable Long id) {
        return ResponseEntity.ok(supplyChainService.getShipmentById(id));
    }

    @PostMapping("/shipments")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'WAREHOUSE_MANAGER', 'SALES_MANAGER')")
    @Operation(summary = "Dispatch / Create a shipment")
    public ResponseEntity<ShipmentDto> createShipment(@Valid @RequestBody ShipmentDto shipmentDto) {
        ShipmentDto created = supplyChainService.createShipment(shipmentDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/shipments/{id}/status")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'WAREHOUSE_MANAGER')")
    @Operation(summary = "Update shipment status (PREPARING, SHIPPED, IN_TRANSIT, OUT_FOR_DELIVERY, DELIVERED)")
    public ResponseEntity<ShipmentDto> updateShipmentStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> payload) {
        String status = payload.get("status");
        return ResponseEntity.ok(supplyChainService.updateShipmentStatus(id, status));
    }
}
