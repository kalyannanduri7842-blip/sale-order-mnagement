package com.enterprisepro.erp.service.impl;

import com.enterprisepro.erp.dto.WarehouseDto;
import com.enterprisepro.erp.entity.Warehouse;
import com.enterprisepro.erp.exception.BadRequestException;
import com.enterprisepro.erp.exception.ResourceNotFoundException;
import com.enterprisepro.erp.repository.WarehouseRepository;
import com.enterprisepro.erp.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<WarehouseDto> getAllWarehouses(String search, Pageable pageable) {
        if (StringUtils.hasText(search)) {
            return warehouseRepository.findByNameContainingIgnoreCaseOrCodeContainingIgnoreCaseOrCityContainingIgnoreCase(
                    search, search, search, pageable).map(this::mapToDto);
        }
        return warehouseRepository.findAll(pageable).map(this::mapToDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WarehouseDto> getActiveWarehouses() {
        return warehouseRepository.findByActiveTrue().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public WarehouseDto getWarehouseById(Long id) {
        Warehouse wh = warehouseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse", "id", id));
        return mapToDto(wh);
    }

    @Override
    @Transactional
    public WarehouseDto createWarehouse(WarehouseDto dto) {
        if (warehouseRepository.findByCode(dto.getCode()).isPresent()) {
            throw new BadRequestException("Warehouse with code '" + dto.getCode() + "' already exists!");
        }

        Warehouse wh = new Warehouse();
        wh.setCode(dto.getCode());
        wh.setName(dto.getName());
        wh.setAddress(dto.getAddress());
        wh.setCity(dto.getCity());
        wh.setState(dto.getState());
        wh.setCountry(dto.getCountry());
        wh.setManagerName(dto.getManagerName());
        wh.setContactPhone(dto.getContactPhone());
        wh.setContactEmail(dto.getContactEmail());
        wh.setCapacity(dto.getCapacity());
        wh.setActive(true);

        Warehouse saved = warehouseRepository.save(wh);
        return mapToDto(saved);
    }

    @Override
    @Transactional
    public WarehouseDto updateWarehouse(Long id, WarehouseDto dto) {
        Warehouse wh = warehouseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse", "id", id));

        wh.setName(dto.getName());
        wh.setAddress(dto.getAddress());
        wh.setCity(dto.getCity());
        wh.setState(dto.getState());
        wh.setCountry(dto.getCountry());
        wh.setManagerName(dto.getManagerName());
        wh.setContactPhone(dto.getContactPhone());
        wh.setContactEmail(dto.getContactEmail());
        wh.setCapacity(dto.getCapacity());
        wh.setActive(dto.isActive());

        Warehouse updated = warehouseRepository.save(wh);
        return mapToDto(updated);
    }

    @Override
    @Transactional
    public void deleteWarehouse(Long id) {
        Warehouse wh = warehouseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse", "id", id));
        wh.setActive(false);
        warehouseRepository.save(wh);
    }

    private WarehouseDto mapToDto(Warehouse wh) {
        WarehouseDto dto = new WarehouseDto();
        dto.setId(wh.getId());
        dto.setCode(wh.getCode());
        dto.setName(wh.getName());
        dto.setAddress(wh.getAddress());
        dto.setCity(wh.getCity());
        dto.setState(wh.getState());
        dto.setCountry(wh.getCountry());
        dto.setManagerName(wh.getManagerName());
        dto.setContactPhone(wh.getContactPhone());
        dto.setContactEmail(wh.getContactEmail());
        dto.setCapacity(wh.getCapacity());
        dto.setActive(wh.isActive());
        return dto;
    }
}
