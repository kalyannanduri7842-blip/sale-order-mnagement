package com.enterprisepro.erp.controller;

import com.enterprisepro.erp.dto.BillOfMaterialsDto;
import com.enterprisepro.erp.dto.WorkOrderDto;
import com.enterprisepro.erp.service.ManufacturingService;
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
@RequestMapping("/api/manufacturing")
@Tag(name = "Manufacturing & Production", description = "Bill of Materials recipes, production schedules, work orders and assembly floor logs")
public class ManufacturingController {

    @Autowired
    private ManufacturingService manufacturingService;

    // BOM
    @GetMapping("/boms")
    @Operation(summary = "Get paginated Bills of Materials")
    public ResponseEntity<Page<BillOfMaterialsDto>> getAllBoms(Pageable pageable) {
        return ResponseEntity.ok(manufacturingService.getAllBoms(pageable));
    }

    @GetMapping("/boms/{id}")
    @Operation(summary = "Get BOM details by ID")
    public ResponseEntity<BillOfMaterialsDto> getBomById(@PathVariable Long id) {
        return ResponseEntity.ok(manufacturingService.getBomById(id));
    }

    @PostMapping("/boms")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'MANUFACTURING_MANAGER')")
    @Operation(summary = "Create a Bill of Materials recipe")
    public ResponseEntity<BillOfMaterialsDto> createBom(@Valid @RequestBody BillOfMaterialsDto bomDto) {
        BillOfMaterialsDto created = manufacturingService.createBom(bomDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // Work Orders
    @GetMapping("/work-orders")
    @Operation(summary = "Get paginated work orders")
    public ResponseEntity<Page<WorkOrderDto>> getAllWorkOrders(
            @RequestParam(required = false) String status,
            Pageable pageable) {
        return ResponseEntity.ok(manufacturingService.getAllWorkOrders(status, pageable));
    }

    @GetMapping("/work-orders/{id}")
    @Operation(summary = "Get work order details by ID")
    public ResponseEntity<WorkOrderDto> getWorkOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(manufacturingService.getWorkOrderById(id));
    }

    @PostMapping("/work-orders")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'MANUFACTURING_MANAGER')")
    @Operation(summary = "Launch a manufacturing work order")
    public ResponseEntity<WorkOrderDto> createWorkOrder(@Valid @RequestBody WorkOrderDto workOrderDto) {
        WorkOrderDto created = manufacturingService.createWorkOrder(workOrderDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/work-orders/{id}/complete")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'MANUFACTURING_MANAGER')")
    @Operation(summary = "Complete production run, yield stock and record yield/scrappage")
    public ResponseEntity<WorkOrderDto> completeWorkOrder(
            @PathVariable Long id,
            @RequestBody Map<String, Integer> payload) {
        int produced = payload.getOrDefault("producedQuantity", 0);
        int scrapped = payload.getOrDefault("scrappedQuantity", 0);
        return ResponseEntity.ok(manufacturingService.completeWorkOrder(id, produced, scrapped));
    }
}
