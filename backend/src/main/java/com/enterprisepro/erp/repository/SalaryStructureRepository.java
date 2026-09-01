package com.enterprisepro.erp.repository;

import com.enterprisepro.erp.entity.Employee;
import com.enterprisepro.erp.entity.SalaryStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalaryStructureRepository extends JpaRepository<SalaryStructure, Long> {
    Optional<SalaryStructure> findByEmployee(Employee employee);
}
