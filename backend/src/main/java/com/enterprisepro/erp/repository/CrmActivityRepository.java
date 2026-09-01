package com.enterprisepro.erp.repository;

import com.enterprisepro.erp.entity.CrmActivity;
import com.enterprisepro.erp.entity.Lead;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrmActivityRepository extends JpaRepository<CrmActivity, Long> {
    List<CrmActivity> findByLeadOrderByActivityDateDesc(Lead lead);
    Page<CrmActivity> findAllByOrderByActivityDateDesc(Pageable pageable);
}
