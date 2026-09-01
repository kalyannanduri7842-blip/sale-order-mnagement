package com.enterprisepro.erp.service;

import com.enterprisepro.erp.dto.AuditLogDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuditService {
    Page<AuditLogDto> getAuditLogs(String search, Pageable pageable);
    void logActivity(String username, String action, String module, String description, String ipAddress);
}
