package com.enterprisepro.erp.service;

import com.enterprisepro.erp.dto.EmployeeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    Page<EmployeeDto> getAllEmployees(String search, Pageable pageable);
    List<EmployeeDto> getAllActiveEmployees();
    EmployeeDto getEmployeeById(Long id);
    EmployeeDto getEmployeeByCode(String code);
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto);
    void deleteEmployee(Long id);
}
