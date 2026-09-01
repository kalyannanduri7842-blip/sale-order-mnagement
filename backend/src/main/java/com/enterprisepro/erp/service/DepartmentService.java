package com.enterprisepro.erp.service;

import com.enterprisepro.erp.dto.DepartmentDto;
import com.enterprisepro.erp.dto.DesignationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DepartmentService {
    Page<DepartmentDto> getAllDepartments(String search, Pageable pageable);
    List<DepartmentDto> getActiveDepartments();
    DepartmentDto getDepartmentById(Long id);
    DepartmentDto createDepartment(DepartmentDto departmentDto);
    DepartmentDto updateDepartment(Long id, DepartmentDto departmentDto);
    void deleteDepartment(Long id);

    // Designations
    List<DesignationDto> getAllDesignations();
    List<DesignationDto> getDesignationsByDepartment(Long departmentId);
    DesignationDto createDesignation(DesignationDto designationDto);
    DesignationDto updateDesignation(Long id, DesignationDto designationDto);
    void deleteDesignation(Long id);
}
