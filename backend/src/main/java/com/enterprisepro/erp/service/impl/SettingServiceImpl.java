package com.enterprisepro.erp.service.impl;

import com.enterprisepro.erp.dto.CompanySettingDto;
import com.enterprisepro.erp.entity.CompanySetting;
import com.enterprisepro.erp.repository.CompanySettingRepository;
import com.enterprisepro.erp.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SettingServiceImpl implements SettingService {

    @Autowired
    private CompanySettingRepository settingRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CompanySettingDto> getAllSettings() {
        return settingRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, String> getSettingsMap() {
        return settingRepository.findAll().stream()
                .collect(Collectors.toMap(CompanySetting::getSettingKey, CompanySetting::getSettingValue, (v1, v2) -> v2));
    }

    @Override
    @Transactional(readOnly = true)
    public String getSettingValue(String key, String defaultValue) {
        return settingRepository.findBySettingKey(key)
                .map(CompanySetting::getSettingValue)
                .orElse(defaultValue);
    }

    @Override
    @Transactional
    public CompanySettingDto saveOrUpdateSetting(String key, String value, String category, String description) {
        Optional<CompanySetting> existing = settingRepository.findBySettingKey(key);
        CompanySetting setting = existing.orElse(new CompanySetting());
        setting.setSettingKey(key);
        setting.setSettingValue(value);
        if (category != null) setting.setCategory(category);
        if (description != null) setting.setDescription(description);
        setting.setUpdatedAt(LocalDateTime.now());

        CompanySetting saved = settingRepository.save(setting);
        return mapToDto(saved);
    }

    private CompanySettingDto mapToDto(CompanySetting setting) {
        CompanySettingDto dto = new CompanySettingDto();
        dto.setId(setting.getId());
        dto.setSettingKey(setting.getSettingKey());
        dto.setSettingValue(setting.getSettingValue());
        dto.setCategory(setting.getCategory());
        dto.setDescription(setting.getDescription());
        dto.setUpdatedAt(setting.getUpdatedAt());
        return dto;
    }
}
