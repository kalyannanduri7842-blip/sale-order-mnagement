package com.enterprisepro.erp.repository;

import com.enterprisepro.erp.entity.Employee;
import com.enterprisepro.erp.entity.LeaveRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<Employee> findByEmployee(Employee employee);
    List<LeaveRequest> findByEmployeeOrderByCreatedAtDesc(Employee employee);
    List<LeaveRequest> findByStatus(String status);
    
    @Query("SELECT COUNT(l) FROM LeaveRequest l WHERE l.status = 'PENDING'")
    long countPendingLeaves();

    Page<LeaveRequest> findByStatus(String status, Pageable pageable);
}
