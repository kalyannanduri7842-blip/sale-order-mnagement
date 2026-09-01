package com.enterprisepro.erp.service;

import com.enterprisepro.erp.dto.LeadDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CrmService {
    Page<LeadDto> getAllLeads(String search, Pageable pageable);
    List<LeadDto> getLeadsByStage(String stage);
    LeadDto getLeadById(Long id);
    LeadDto createLead(LeadDto leadDto);
    LeadDto updateLeadStage(Long id, String stage);
    void deleteLead(Long id);
}
