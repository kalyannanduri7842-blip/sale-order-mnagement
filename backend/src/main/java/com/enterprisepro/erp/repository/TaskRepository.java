package com.enterprisepro.erp.repository;

import com.enterprisepro.erp.entity.Employee;
import com.enterprisepro.erp.entity.Project;
import com.enterprisepro.erp.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByProject(Project project);
    List<Task> findByAssignedTo(Employee employee);
    List<Task> findByStatus(String status);
    Page<Task> findByTitleContainingIgnoreCaseOrStatusContainingIgnoreCase(
            String title, String status, Pageable pageable);
}
