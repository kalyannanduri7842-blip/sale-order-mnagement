package com.enterprisepro.erp.service.impl;

import com.enterprisepro.erp.dto.AssetDto;
import com.enterprisepro.erp.entity.Asset;
import com.enterprisepro.erp.entity.Employee;
import com.enterprisepro.erp.exception.ResourceNotFoundException;
import com.enterprisepro.erp.repository.AssetRepository;
import com.enterprisepro.erp.repository.EmployeeRepository;
import com.enterprisepro.erp.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<AssetDto> getAllAssets(String search, Pageable pageable) {
        if (StringUtils.hasText(search)) {
            return assetRepository.findByNameContainingIgnoreCaseOrAssetCodeContainingIgnoreCaseOrCategoryContainingIgnoreCase(
                    search, search, search, pageable).map(this::mapToDto);
        }
        return assetRepository.findAll(pageable).map(this::mapToDto);
    }

    @Override
    @Transactional(readOnly = true)
    public AssetDto getAssetById(Long id) {
        Asset asset = assetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asset", "id", id));
        return mapToDto(asset);
    }

    @Override
    @Transactional
    public AssetDto createAsset(AssetDto dto) {
        Asset a = new Asset();
        a.setAssetCode(StringUtils.hasText(dto.getAssetCode()) ? dto.getAssetCode() : "AST-" + (1000 + assetRepository.count() + 1));
        a.setName(dto.getName());
        a.setCategory(dto.getCategory() != null ? dto.getCategory() : "HARDWARE");
        a.setSerialNumber(dto.getSerialNumber());
        a.setPurchaseDate(dto.getPurchaseDate() != null ? dto.getPurchaseDate() : LocalDate.now());
        a.setPurchaseCost(dto.getPurchaseCost());
        a.setCurrentValuation(dto.getCurrentValuation() != null ? dto.getCurrentValuation() : dto.getPurchaseCost());
        a.setUsefulLifeYears(dto.getUsefulLifeYears() > 0 ? dto.getUsefulLifeYears() : 5);
        a.setSalvageValue(dto.getSalvageValue());
        a.setLocation(dto.getLocation());
        a.setStatus(dto.getStatus() != null ? dto.getStatus() : "OPERATIONAL");
        a.setNotes(dto.getNotes());

        if (dto.getAssignedEmployeeId() != null) {
            Employee emp = employeeRepository.findById(dto.getAssignedEmployeeId()).orElse(null);
            a.setAssignedTo(emp);
        }

        Asset saved = assetRepository.save(a);
        return mapToDto(saved);
    }

    @Override
    @Transactional
    public AssetDto updateAsset(Long id, AssetDto dto) {
        Asset a = assetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asset", "id", id));

        a.setName(dto.getName());
        a.setCategory(dto.getCategory());
        a.setSerialNumber(dto.getSerialNumber());
        a.setPurchaseCost(dto.getPurchaseCost());
        a.setCurrentValuation(dto.getCurrentValuation());
        a.setUsefulLifeYears(dto.getUsefulLifeYears());
        a.setSalvageValue(dto.getSalvageValue());
        a.setLocation(dto.getLocation());
        a.setStatus(dto.getStatus());
        a.setNotes(dto.getNotes());

        if (dto.getAssignedEmployeeId() != null) {
            Employee emp = employeeRepository.findById(dto.getAssignedEmployeeId()).orElse(null);
            a.setAssignedTo(emp);
        }

        Asset updated = assetRepository.save(a);
        return mapToDto(updated);
    }

    @Override
    @Transactional
    public void deleteAsset(Long id) {
        Asset a = assetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asset", "id", id));
        a.setStatus("DISPOSED");
        assetRepository.save(a);
    }

    private AssetDto mapToDto(Asset a) {
        AssetDto dto = new AssetDto();
        dto.setId(a.getId());
        dto.setAssetCode(a.getAssetCode());
        dto.setName(a.getName());
        dto.setCategory(a.getCategory());
        dto.setSerialNumber(a.getSerialNumber());
        dto.setPurchaseDate(a.getPurchaseDate());
        dto.setPurchaseCost(a.getPurchaseCost());
        dto.setCurrentValuation(a.getCurrentValuation());
        dto.setUsefulLifeYears(a.getUsefulLifeYears());
        dto.setSalvageValue(a.getSalvageValue());
        dto.setLocation(a.getLocation());
        dto.setStatus(a.getStatus());
        dto.setNotes(a.getNotes());
        if (a.getAssignedTo() != null) {
            dto.setAssignedEmployeeId(a.getAssignedTo().getId());
            dto.setAssignedEmployeeName(a.getAssignedTo().getFullName());
        }
        return dto;
    }
}
