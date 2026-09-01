package com.enterprisepro.erp.service;

import com.enterprisepro.erp.dto.VendorDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VendorService {
    Page<VendorDto> getAllVendors(String search, Pageable pageable);
    List<VendorDto> getActiveVendors();
    VendorDto getVendorById(Long id);
    VendorDto createVendor(VendorDto vendorDto);
    VendorDto updateVendor(Long id, VendorDto vendorDto);
    void deleteVendor(Long id);
}
