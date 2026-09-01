package com.enterprisepro.erp.service;

import com.enterprisepro.erp.dto.CompanySettingDto;

import java.util.List;
import java.util.Map;

public interface SettingService {
    List<CompanySettingDto> getAllSettings();
    Map<String, String> getSettingsMap();
    String getSettingValue(String key, String defaultValue);
    CompanySettingDto saveOrUpdateSetting(String key, String value, String category, String description);
}
