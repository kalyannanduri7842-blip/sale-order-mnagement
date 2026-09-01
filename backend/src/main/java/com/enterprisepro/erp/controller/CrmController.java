package com.enterprisepro.erp.controller;

import com.enterprisepro.erp.dto.LeadDto;
import com.enterprisepro.erp.service.CrmService;
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
import java.util.Map;

@RestController
@RequestMapping("/api/crm")
@Tag(name = "CRM & Opportunity Pipeline", description = "Endpoints for lead tracking, sales funnel stages and conversion metrics")
public class CrmController {

    @Autowired
    private CrmService crmService;

    @GetMapping("/leads")
    @Operation(summary = "Get paginated leads with search")
    public ResponseEntity<Page<LeadDto>> getAllLeads(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(crmService.getAllLeads(search, pageable));
    }

    @GetMapping("/leads/stage/{stage}")
    @Operation(summary = "Get leads by stage for Kanban board view")
    public ResponseEntity<List<LeadDto>> getLeadsByStage(@PathVariable String stage) {
        return ResponseEntity.ok(crmService.getLeadsByStage(stage));
    }

    @GetMapping("/leads/{id}")
    @Operation(summary = "Get lead by ID")
    public ResponseEntity<LeadDto> getLeadById(@PathVariable Long id) {
        return ResponseEntity.ok(crmService.getLeadById(id));
    }

    @PostMapping("/leads")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'CRM_MANAGER', 'SALES_MANAGER')")
    @Operation(summary = "Create a new sales lead")
    public ResponseEntity<LeadDto> createLead(@Valid @RequestBody LeadDto leadDto) {
        LeadDto created = crmService.createLead(leadDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/leads/{id}/stage")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'CRM_MANAGER', 'SALES_MANAGER')")
    @Operation(summary = "Advance lead through pipeline stages (NEW -> CONTACTED -> QUALIFIED -> PROPOSAL -> NEGOTIATION -> WON/LOST)")
    public ResponseEntity<LeadDto> updateLeadStage(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        String stage = payload.get("stage");
        return ResponseEntity.ok(crmService.updateLeadStage(id, stage));
    }

    @DeleteMapping("/leads/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'CRM_MANAGER')")
    @Operation(summary = "Delete a lead")
    public ResponseEntity<Void> deleteLead(@PathVariable Long id) {
        crmService.deleteLead(id);
        return ResponseEntity.noContent().build();
    }
}
