package com.enterprisepro.erp.service;

import com.enterprisepro.erp.dto.WarehouseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WarehouseService {
    Page<WarehouseDto> getAllWarehouses(String search, Pageable pageable);
    List<WarehouseDto> getActiveWarehouses();
    WarehouseDto getWarehouseById(Long id);
    WarehouseDto createWarehouse(WarehouseDto warehouseDto);
    WarehouseDto updateWarehouse(Long id, WarehouseDto warehouseDto);
    void deleteWarehouse(Long id);
}
