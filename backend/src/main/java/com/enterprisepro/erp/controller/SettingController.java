package com.enterprisepro.erp.controller;

import com.enterprisepro.erp.dto.CompanySettingDto;
import com.enterprisepro.erp.service.SettingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/settings")
@Tag(name = "System Configuration & Settings", description = "Global ERP configurations, company info, tax rates, currencies and workflow parameters")
public class SettingController {

    @Autowired
    private SettingService settingService;

    @GetMapping
    @Operation(summary = "Get all system configuration parameters")
    public ResponseEntity<List<CompanySettingDto>> getAllSettings() {
        return ResponseEntity.ok(settingService.getAllSettings());
    }

    @GetMapping("/map")
    @Operation(summary = "Get key-value map of system settings")
    public ResponseEntity<Map<String, String>> getSettingsMap() {
        return ResponseEntity.ok(settingService.getSettingsMap());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN')")
    @Operation(summary = "Save or update a company setting")
    public ResponseEntity<CompanySettingDto> saveSetting(@RequestBody Map<String, String> payload) {
        String key = payload.get("key");
        String value = payload.get("value");
        String category = payload.get("category");
        String description = payload.get("description");

        CompanySettingDto setting = settingService.saveOrUpdateSetting(key, value, category, description);
        return ResponseEntity.ok(setting);
    }
}
