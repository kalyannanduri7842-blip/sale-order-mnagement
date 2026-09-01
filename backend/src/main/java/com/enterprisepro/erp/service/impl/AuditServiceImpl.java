package com.enterprisepro.erp.service.impl;

import com.enterprisepro.erp.dto.AuditLogDto;
import com.enterprisepro.erp.entity.AuditLog;
import com.enterprisepro.erp.repository.AuditLogRepository;
import com.enterprisepro.erp.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@Service
public class AuditServiceImpl implements AuditService {

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<AuditLogDto> getAuditLogs(String search, Pageable pageable) {
        if (StringUtils.hasText(search)) {
            return auditLogRepository.findByUsernameContainingIgnoreCaseOrModuleContainingIgnoreCaseOrActionContainingIgnoreCase(
                    search, search, search, pageable).map(this::mapToDto);
        }
        return auditLogRepository.findAllByOrderByTimestampDesc(pageable).map(this::mapToDto);
    }

    @Override
    @Async
    @Transactional
    public void logActivity(String username, String action, String module, String description, String ipAddress) {
        AuditLog log = new AuditLog();
        log.setUsername(username != null ? username : "SYSTEM");
        log.setAction(action);
        log.setModule(module);
        log.setDescription(description);
        log.setIpAddress(ipAddress != null ? ipAddress : "127.0.0.1");
        log.setTimestamp(LocalDateTime.now());
        auditLogRepository.save(log);
    }

    private AuditLogDto mapToDto(AuditLog log) {
        AuditLogDto dto = new AuditLogDto();
        dto.setId(log.getId());
        dto.setUsername(log.getUsername());
        dto.setAction(log.getAction());
        dto.setModule(log.getModule());
        dto.setDescription(log.getDescription());
        dto.setIpAddress(log.getIpAddress());
        dto.setUserAgent(log.getUserAgent());
        dto.setTimestamp(log.getTimestamp());
        return dto;
    }
}
