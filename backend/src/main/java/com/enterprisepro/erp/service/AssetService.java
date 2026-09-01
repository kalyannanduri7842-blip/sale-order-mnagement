package com.enterprisepro.erp.service;

import com.enterprisepro.erp.dto.AssetDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AssetService {
    Page<AssetDto> getAllAssets(String search, Pageable pageable);
    AssetDto getAssetById(Long id);
    AssetDto createAsset(AssetDto assetDto);
    AssetDto updateAsset(Long id, AssetDto assetDto);
    void deleteAsset(Long id);
}
