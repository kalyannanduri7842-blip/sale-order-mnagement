package com.enterprisepro.erp.service.impl;

import com.enterprisepro.erp.dto.VendorDto;
import com.enterprisepro.erp.entity.Vendor;
import com.enterprisepro.erp.exception.BadRequestException;
import com.enterprisepro.erp.exception.ResourceNotFoundException;
import com.enterprisepro.erp.repository.VendorRepository;
import com.enterprisepro.erp.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<VendorDto> getAllVendors(String search, Pageable pageable) {
        if (StringUtils.hasText(search)) {
            return vendorRepository.findByCompanyNameContainingIgnoreCaseOrVendorCodeContainingIgnoreCaseOrContactPersonContainingIgnoreCase(
                    search, search, search, pageable).map(this::mapToDto);
        }
        return vendorRepository.findAll(pageable).map(this::mapToDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VendorDto> getActiveVendors() {
        return vendorRepository.findByActiveTrue().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public VendorDto getVendorById(Long id) {
        Vendor v = vendorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor", "id", id));
        return mapToDto(v);
    }

    @Override
    @Transactional
    public VendorDto createVendor(VendorDto dto) {
        if (vendorRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new BadRequestException("Vendor with email '" + dto.getEmail() + "' already exists!");
        }

        Vendor v = new Vendor();
        v.setVendorCode(StringUtils.hasText(dto.getVendorCode()) ? dto.getVendorCode() : "VEN-" + (1000 + vendorRepository.count() + 1));
        v.setCompanyName(dto.getCompanyName());
        v.setContactPerson(dto.getContactPerson());
        v.setEmail(dto.getEmail());
        v.setPhone(dto.getPhone());
        v.setAddress(dto.getAddress());
        v.setCity(dto.getCity());
        v.setCountry(dto.getCountry());
        v.setTaxId(dto.getTaxId());
        v.setPaymentTerms(dto.getPaymentTerms() != null ? dto.getPaymentTerms() : "NET_30");
        v.setRating(dto.getRating() > 0 ? dto.getRating() : 5.0);
        v.setNotes(dto.getNotes());
        v.setActive(true);

        Vendor saved = vendorRepository.save(v);
        return mapToDto(saved);
    }

    @Override
    @Transactional
    public VendorDto updateVendor(Long id, VendorDto dto) {
        Vendor v = vendorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor", "id", id));

        v.setCompanyName(dto.getCompanyName());
        v.setContactPerson(dto.getContactPerson());
        v.setEmail(dto.getEmail());
        v.setPhone(dto.getPhone());
        v.setAddress(dto.getAddress());
        v.setCity(dto.getCity());
        v.setCountry(dto.getCountry());
        v.setTaxId(dto.getTaxId());
        v.setPaymentTerms(dto.getPaymentTerms());
        v.setRating(dto.getRating());
        v.setNotes(dto.getNotes());
        v.setActive(dto.isActive());

        Vendor updated = vendorRepository.save(v);
        return mapToDto(updated);
    }

    @Override
    @Transactional
    public void deleteVendor(Long id) {
        Vendor v = vendorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor", "id", id));
        v.setActive(false);
        vendorRepository.save(v);
    }

    private VendorDto mapToDto(Vendor v) {
        VendorDto dto = new VendorDto();
        dto.setId(v.getId());
        dto.setVendorCode(v.getVendorCode());
        dto.setCompanyName(v.getCompanyName());
        dto.setContactPerson(v.getContactPerson());
        dto.setEmail(v.getEmail());
        dto.setPhone(v.getPhone());
        dto.setAddress(v.getAddress());
        dto.setCity(v.getCity());
        dto.setCountry(v.getCountry());
        dto.setTaxId(v.getTaxId());
        dto.setPaymentTerms(v.getPaymentTerms());
        dto.setRating(v.getRating());
        dto.setNotes(v.getNotes());
        dto.setActive(v.isActive());
        return dto;
    }
}
