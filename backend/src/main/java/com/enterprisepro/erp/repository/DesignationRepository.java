package com.enterprisepro.erp.repository;

import com.enterprisepro.erp.entity.Department;
import com.enterprisepro.erp.entity.Designation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesignationRepository extends JpaRepository<Designation, Long> {
    List<Designation> findByDepartment(Department department);
    List<Designation> findByActiveTrue();
    Page<Designation> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
