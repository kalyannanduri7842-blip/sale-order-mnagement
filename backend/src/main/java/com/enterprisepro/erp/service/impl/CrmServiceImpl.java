package com.enterprisepro.erp.service.impl;

import com.enterprisepro.erp.dto.LeadDto;
import com.enterprisepro.erp.entity.Lead;
import com.enterprisepro.erp.exception.ResourceNotFoundException;
import com.enterprisepro.erp.repository.LeadRepository;
import com.enterprisepro.erp.service.CrmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CrmServiceImpl implements CrmService {

    @Autowired
    private LeadRepository leadRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<LeadDto> getAllLeads(String search, Pageable pageable) {
        if (StringUtils.hasText(search)) {
            return leadRepository.findByNameContainingIgnoreCaseOrCompanyContainingIgnoreCaseOrEmailContainingIgnoreCase(
                    search, search, search, pageable).map(this::mapToDto);
        }
        return leadRepository.findAll(pageable).map(this::mapToDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LeadDto> getLeadsByStage(String stage) {
        return leadRepository.findByStage(stage).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public LeadDto getLeadById(Long id) {
        Lead lead = leadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lead", "id", id));
        return mapToDto(lead);
    }

    @Override
    @Transactional
    public LeadDto createLead(LeadDto dto) {
        Lead lead = new Lead();
        lead.setName(dto.getName());
        lead.setCompany(dto.getCompany());
        lead.setEmail(dto.getEmail());
        lead.setPhone(dto.getPhone());
        lead.setSource(dto.getSource() != null ? dto.getSource() : "WEBSITE");
        lead.setStage(dto.getStage() != null ? dto.getStage() : "NEW");
        lead.setEstimatedValue(dto.getEstimatedValue());
        lead.setProbability(dto.getProbability() > 0 ? dto.getProbability() : 50);
        lead.setNotes(dto.getNotes());

        Lead saved = leadRepository.save(lead);
        return mapToDto(saved);
    }

    @Override
    @Transactional
    public LeadDto updateLeadStage(Long id, String stage) {
        Lead lead = leadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lead", "id", id));
        lead.setStage(stage.toUpperCase());

        if ("WON".equalsIgnoreCase(stage)) {
            lead.setProbability(100);
        } else if ("LOST".equalsIgnoreCase(stage)) {
            lead.setProbability(0);
        }

        Lead updated = leadRepository.save(lead);
        return mapToDto(updated);
    }

    @Override
    @Transactional
    public void deleteLead(Long id) {
        Lead lead = leadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lead", "id", id));
        leadRepository.delete(lead);
    }

    private LeadDto mapToDto(Lead lead) {
        LeadDto dto = new LeadDto();
        dto.setId(lead.getId());
        dto.setName(lead.getName());
        dto.setCompany(lead.getCompany());
        dto.setEmail(lead.getEmail());
        dto.setPhone(lead.getPhone());
        dto.setSource(lead.getSource());
        dto.setStage(lead.getStage());
        dto.setEstimatedValue(lead.getEstimatedValue());
        dto.setProbability(lead.getProbability());
        dto.setNotes(lead.getNotes());
        if (lead.getAssignedTo() != null) {
            dto.setAssignedToUsername(lead.getAssignedTo().getUsername());
        }
        return dto;
    }
}
