package com.enterprisepro.erp.repository;

import com.enterprisepro.erp.entity.Lead;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Long> {
    List<Lead> findByStage(String stage);
    
    @Query("SELECT SUM(l.estimatedValue) FROM Lead l WHERE l.stage != 'LOST'")
    BigDecimal sumPipelineValue();

    Page<Lead> findByNameContainingIgnoreCaseOrCompanyContainingIgnoreCaseOrEmailContainingIgnoreCase(
            String name, String company, String email, Pageable pageable);
}
