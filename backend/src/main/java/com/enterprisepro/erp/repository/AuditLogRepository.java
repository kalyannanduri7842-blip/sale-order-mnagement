package com.enterprisepro.erp.repository;

import com.enterprisepro.erp.entity.AuditLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    List<AuditLog> findByModuleOrderByTimestampDesc(String module);
    List<AuditLog> findByUsernameOrderByTimestampDesc(String username);
    Page<AuditLog> findAllByOrderByTimestampDesc(Pageable pageable);
    Page<AuditLog> findByUsernameContainingIgnoreCaseOrModuleContainingIgnoreCaseOrActionContainingIgnoreCase(
            String username, String module, String action, Pageable pageable);
}
