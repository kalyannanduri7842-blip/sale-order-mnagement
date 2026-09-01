package com.enterprisepro.erp.service.impl;

import com.enterprisepro.erp.dto.EmployeeDto;
import com.enterprisepro.erp.entity.Department;
import com.enterprisepro.erp.entity.Designation;
import com.enterprisepro.erp.entity.Employee;
import com.enterprisepro.erp.exception.BadRequestException;
import com.enterprisepro.erp.exception.ResourceNotFoundException;
import com.enterprisepro.erp.repository.DepartmentRepository;
import com.enterprisepro.erp.repository.DesignationRepository;
import com.enterprisepro.erp.repository.EmployeeRepository;
import com.enterprisepro.erp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DesignationRepository designationRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<EmployeeDto> getAllEmployees(String search, Pageable pageable) {
        if (StringUtils.hasText(search)) {
            return employeeRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmployeeIdContainingIgnoreCaseOrEmailContainingIgnoreCase(
                    search, search, search, search, pageable).map(this::mapToDto);
        }
        return employeeRepository.findAll(pageable).map(this::mapToDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDto> getAllActiveEmployees() {
        return employeeRepository.findByEmploymentStatus("ACTIVE").stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        return mapToDto(employee);
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeDto getEmployeeByCode(String code) {
        Employee employee = employeeRepository.findByEmployeeId(code)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "code", code));
        return mapToDto(employee);
    }

    @Override
    @Transactional
    public EmployeeDto createEmployee(EmployeeDto dto) {
        if (employeeRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new BadRequestException("Employee with email " + dto.getEmail() + " already exists!");
        }

        Employee employee = new Employee();
        String empCode = dto.getEmployeeId();
        if (!StringUtils.hasText(empCode)) {
            empCode = "EMP-" + (1000 + employeeRepository.count() + 1);
        }
        employee.setEmployeeId(empCode);
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setAddress(dto.getAddress());
        employee.setDateOfBirth(dto.getDateOfBirth());
        employee.setGender(dto.getGender());
        employee.setJoiningDate(dto.getJoiningDate());
        employee.setSalary(dto.getSalary());
        employee.setEmploymentStatus(dto.getEmploymentStatus() != null ? dto.getEmploymentStatus() : "ACTIVE");
        employee.setEmploymentType(dto.getEmploymentType() != null ? dto.getEmploymentType() : "FULL_TIME");
        employee.setAvatar(dto.getAvatar());

        if (dto.getDepartmentId() != null) {
            Department department = departmentRepository.findById(dto.getDepartmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department", "id", dto.getDepartmentId()));
            employee.setDepartment(department);
        }

        if (dto.getDesignationId() != null) {
            Designation designation = designationRepository.findById(dto.getDesignationId())
                    .orElseThrow(() -> new ResourceNotFoundException("Designation", "id", dto.getDesignationId()));
            employee.setDesignation(designation);
        }

        if (dto.getManagerId() != null) {
            Employee manager = employeeRepository.findById(dto.getManagerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Manager", "id", dto.getManagerId()));
            employee.setManager(manager);
        }

        Employee saved = employeeRepository.save(employee);
        return mapToDto(saved);
    }

    @Override
    @Transactional
    public EmployeeDto updateEmployee(Long id, EmployeeDto dto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));

        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setAddress(dto.getAddress());
        employee.setDateOfBirth(dto.getDateOfBirth());
        employee.setGender(dto.getGender());
        employee.setJoiningDate(dto.getJoiningDate());
        employee.setSalary(dto.getSalary());
        if (dto.getEmploymentStatus() != null) employee.setEmploymentStatus(dto.getEmploymentStatus());
        if (dto.getEmploymentType() != null) employee.setEmploymentType(dto.getEmploymentType());
        if (dto.getAvatar() != null) employee.setAvatar(dto.getAvatar());

        if (dto.getDepartmentId() != null) {
            Department department = departmentRepository.findById(dto.getDepartmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department", "id", dto.getDepartmentId()));
            employee.setDepartment(department);
        }

        if (dto.getDesignationId() != null) {
            Designation designation = designationRepository.findById(dto.getDesignationId())
                    .orElseThrow(() -> new ResourceNotFoundException("Designation", "id", dto.getDesignationId()));
            employee.setDesignation(designation);
        }

        if (dto.getManagerId() != null) {
            Employee manager = employeeRepository.findById(dto.getManagerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Manager", "id", dto.getManagerId()));
            employee.setManager(manager);
        }

        Employee updated = employeeRepository.save(employee);
        return mapToDto(updated);
    }

    @Override
    @Transactional
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        employee.setEmploymentStatus("TERMINATED");
        employeeRepository.save(employee);
    }

    private EmployeeDto mapToDto(Employee employee) {
        EmployeeDto dto = new EmployeeDto();
        dto.setId(employee.getId());
        dto.setEmployeeId(employee.getEmployeeId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setEmail(employee.getEmail());
        dto.setPhone(employee.getPhone());
        dto.setAddress(employee.getAddress());
        dto.setDateOfBirth(employee.getDateOfBirth());
        dto.setGender(employee.getGender());
        dto.setJoiningDate(employee.getJoiningDate());
        dto.setSalary(employee.getSalary());
        dto.setEmploymentStatus(employee.getEmploymentStatus());
        dto.setEmploymentType(employee.getEmploymentType());
        dto.setAvatar(employee.getAvatar());

        if (employee.getDepartment() != null) {
            dto.setDepartmentId(employee.getDepartment().getId());
            dto.setDepartmentName(employee.getDepartment().getName());
        }

        if (employee.getDesignation() != null) {
            dto.setDesignationId(employee.getDesignation().getId());
            dto.setDesignationTitle(employee.getDesignation().getTitle());
        }

        if (employee.getManager() != null) {
            dto.setManagerId(employee.getManager().getId());
            dto.setManagerName(employee.getManager().getFullName());
        }

        return dto;
    }
}
