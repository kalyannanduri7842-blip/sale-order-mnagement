package com.enterprisepro.erp.service.impl;

import com.enterprisepro.erp.dto.DepartmentDto;
import com.enterprisepro.erp.dto.DesignationDto;
import com.enterprisepro.erp.entity.Department;
import com.enterprisepro.erp.entity.Designation;
import com.enterprisepro.erp.exception.BadRequestException;
import com.enterprisepro.erp.exception.ResourceNotFoundException;
import com.enterprisepro.erp.repository.DepartmentRepository;
import com.enterprisepro.erp.repository.DesignationRepository;
import com.enterprisepro.erp.repository.EmployeeRepository;
import com.enterprisepro.erp.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DesignationRepository designationRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<DepartmentDto> getAllDepartments(String search, Pageable pageable) {
        if (StringUtils.hasText(search)) {
            return departmentRepository.findByNameContainingIgnoreCaseOrCodeContainingIgnoreCase(search, search, pageable)
                    .map(this::mapDeptToDto);
        }
        return departmentRepository.findAll(pageable).map(this::mapDeptToDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DepartmentDto> getActiveDepartments() {
        return departmentRepository.findByActiveTrue().stream()
                .map(this::mapDeptToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public DepartmentDto getDepartmentById(Long id) {
        Department dept = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", id));
        return mapDeptToDto(dept);
    }

    @Override
    @Transactional
    public DepartmentDto createDepartment(DepartmentDto dto) {
        if (departmentRepository.findByName(dto.getName()).isPresent()) {
            throw new BadRequestException("Department with name '" + dto.getName() + "' already exists!");
        }

        Department dept = new Department(dto.getName(), dto.getCode(), dto.getDescription(), dto.getHeadOfDepartment());
        Department saved = departmentRepository.save(dept);
        return mapDeptToDto(saved);
    }

    @Override
    @Transactional
    public DepartmentDto updateDepartment(Long id, DepartmentDto dto) {
        Department dept = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", id));

        dept.setName(dto.getName());
        dept.setCode(dto.getCode());
        dept.setDescription(dto.getDescription());
        dept.setHeadOfDepartment(dto.getHeadOfDepartment());
        dept.setActive(dto.isActive());

        Department updated = departmentRepository.save(dept);
        return mapDeptToDto(updated);
    }

    @Override
    @Transactional
    public void deleteDepartment(Long id) {
        Department dept = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", id));
        dept.setActive(false);
        departmentRepository.save(dept);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DesignationDto> getAllDesignations() {
        return designationRepository.findAll().stream()
                .map(this::mapDesigToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<DesignationDto> getDesignationsByDepartment(Long departmentId) {
        Department dept = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", departmentId));
        return designationRepository.findByDepartment(dept).stream()
                .map(this::mapDesigToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public DesignationDto createDesignation(DesignationDto dto) {
        Department dept = null;
        if (dto.getDepartmentId() != null) {
            dept = departmentRepository.findById(dto.getDepartmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department", "id", dto.getDepartmentId()));
        }

        Designation desig = new Designation(dto.getTitle(), dept, dto.getMinSalary(), dto.getMaxSalary(), dto.getDescription());
        Designation saved = designationRepository.save(desig);
        return mapDesigToDto(saved);
    }

    @Override
    @Transactional
    public DesignationDto updateDesignation(Long id, DesignationDto dto) {
        Designation desig = designationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Designation", "id", id));

        desig.setTitle(dto.getTitle());
        desig.setMinSalary(dto.getMinSalary());
        desig.setMaxSalary(dto.getMaxSalary());
        desig.setDescription(dto.getDescription());
        desig.setActive(dto.isActive());

        if (dto.getDepartmentId() != null) {
            Department dept = departmentRepository.findById(dto.getDepartmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department", "id", dto.getDepartmentId()));
            desig.setDepartment(dept);
        }

        Designation updated = designationRepository.save(desig);
        return mapDesigToDto(updated);
    }

    @Override
    @Transactional
    public void deleteDesignation(Long id) {
        Designation desig = designationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Designation", "id", id));
        desig.setActive(false);
        designationRepository.save(desig);
    }

    private DepartmentDto mapDeptToDto(Department dept) {
        DepartmentDto dto = new DepartmentDto();
        dto.setId(dept.getId());
        dto.setName(dept.getName());
        dto.setCode(dept.getCode());
        dto.setDescription(dept.getDescription());
        dto.setHeadOfDepartment(dept.getHeadOfDepartment());
        dto.setActive(dept.isActive());
        dto.setEmployeeCount(employeeRepository.findByDepartment(dept).size());
        return dto;
    }

    private DesignationDto mapDesigToDto(Designation desig) {
        DesignationDto dto = new DesignationDto();
        dto.setId(desig.getId());
        dto.setTitle(desig.getTitle());
        dto.setMinSalary(desig.getMinSalary());
        dto.setMaxSalary(desig.getMaxSalary());
        dto.setDescription(desig.getDescription());
        dto.setActive(desig.isActive());
        if (desig.getDepartment() != null) {
            dto.setDepartmentId(desig.getDepartment().getId());
            dto.setDepartmentName(desig.getDepartment().getName());
        }
        return dto;
    }
}
