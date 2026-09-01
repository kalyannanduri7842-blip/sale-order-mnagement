package com.enterprisepro.erp.controller;

import com.enterprisepro.erp.dto.VendorDto;
import com.enterprisepro.erp.service.VendorService;
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
@RequestMapping("/api/vendors")
@Tag(name = "Vendor Management", description = "Endpoints for managing supplier profiles, performance ratings and contact info")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    @GetMapping
    @Operation(summary = "Get paginated vendors with search")
    public ResponseEntity<Page<VendorDto>> getAllVendors(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(vendorService.getAllVendors(search, pageable));
    }

    @GetMapping("/active")
    @Operation(summary = "Get list of active vendors")
    public ResponseEntity<List<VendorDto>> getActiveVendors() {
        return ResponseEntity.ok(vendorService.getActiveVendors());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get vendor by ID")
    public ResponseEntity<VendorDto> getVendorById(@PathVariable Long id) {
        return ResponseEntity.ok(vendorService.getVendorById(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'PROCUREMENT_MANAGER', 'PURCHASE_MANAGER')")
    @Operation(summary = "Create a new vendor")
    public ResponseEntity<VendorDto> createVendor(@Valid @RequestBody VendorDto vendorDto) {
        VendorDto created = vendorService.createVendor(vendorDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'PROCUREMENT_MANAGER', 'PURCHASE_MANAGER')")
    @Operation(summary = "Update an existing vendor")
    public ResponseEntity<VendorDto> updateVendor(@PathVariable Long id, @Valid @RequestBody VendorDto vendorDto) {
        return ResponseEntity.ok(vendorService.updateVendor(id, vendorDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'PROCUREMENT_MANAGER')")
    @Operation(summary = "Deactivate a vendor")
    public ResponseEntity<Void> deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendor(id);
        return ResponseEntity.noContent().build();
    }
}
