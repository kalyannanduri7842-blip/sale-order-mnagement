package com.enterprisepro.erp.controller;

import com.enterprisepro.erp.dto.DepartmentDto;
import com.enterprisepro.erp.dto.DesignationDto;
import com.enterprisepro.erp.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@Tag(name = "Departments & Designations", description = "Endpoints for department and organizational hierarchy management")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    @Operation(summary = "Get paginated list of departments")
    public ResponseEntity<Page<DepartmentDto>> getAllDepartments(
            @RequestParam(required = false) String search,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(departmentService.getAllDepartments(search, pageable));
    }

    @GetMapping("/active")
    @Operation(summary = "Get list of active departments")
    public ResponseEntity<List<DepartmentDto>> getActiveDepartments() {
        return ResponseEntity.ok(departmentService.getActiveDepartments());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get department by ID")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'HR_MANAGER')")
    @Operation(summary = "Create a new department")
    public ResponseEntity<DepartmentDto> createDepartment(@Valid @RequestBody DepartmentDto departmentDto) {
        DepartmentDto created = departmentService.createDepartment(departmentDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'HR_MANAGER')")
    @Operation(summary = "Update an existing department")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable Long id, @Valid @RequestBody DepartmentDto departmentDto) {
        return ResponseEntity.ok(departmentService.updateDepartment(id, departmentDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'HR_MANAGER')")
    @Operation(summary = "Deactivate a department")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }

    // Designations endpoints
    @GetMapping("/designations")
    @Operation(summary = "Get all designations")
    public ResponseEntity<List<DesignationDto>> getAllDesignations() {
        return ResponseEntity.ok(departmentService.getAllDesignations());
    }

    @GetMapping("/{departmentId}/designations")
    @Operation(summary = "Get designations mapped to department")
    public ResponseEntity<List<DesignationDto>> getDesignationsByDepartment(@PathVariable Long departmentId) {
        return ResponseEntity.ok(departmentService.getDesignationsByDepartment(departmentId));
    }

    @PostMapping("/designations")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'HR_MANAGER')")
    @Operation(summary = "Create a new designation")
    public ResponseEntity<DesignationDto> createDesignation(@Valid @RequestBody DesignationDto designationDto) {
        DesignationDto created = departmentService.createDesignation(designationDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/designations/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'HR_MANAGER')")
    @Operation(summary = "Update a designation")
    public ResponseEntity<DesignationDto> updateDesignation(@PathVariable Long id, @Valid @RequestBody DesignationDto designationDto) {
        return ResponseEntity.ok(departmentService.updateDesignation(id, designationDto));
    }

    @DeleteMapping("/designations/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'HR_MANAGER')")
    @Operation(summary = "Deactivate a designation")
    public ResponseEntity<Void> deleteDesignation(@PathVariable Long id) {
        departmentService.deleteDesignation(id);
        return ResponseEntity.noContent().build();
    }
}
