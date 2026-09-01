package com.enterprisepro.erp.repository;

import com.enterprisepro.erp.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findByProjectCode(String projectCode);
    List<Project> findByStatus(String status);

    @Query("SELECT COUNT(p) FROM Project p WHERE p.status = 'COMPLETED'")
    long countCompletedProjects();

    @Query("SELECT COUNT(p) FROM Project p WHERE p.status = 'IN_PROGRESS'")
    long countActiveProjects();

    Page<Project> findByNameContainingIgnoreCaseOrProjectCodeContainingIgnoreCase(
            String name, String projectCode, Pageable pageable);
}
