package com.enterprisepro.erp;

import com.enterprisepro.erp.entity.Category;
import com.enterprisepro.erp.entity.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SmokeTest {

    @Test
    void productLifecycleCreatesValidEntity() {
        Category category = new Category("Hardware", "CAT-HW", "Office technology");
        Product product = new Product(
                "PRD-9001",
                "Laptop Dock",
                "LD-9001",
                BigDecimal.valueOf(250.00),
                BigDecimal.valueOf(399.00),
                12,
                5,
                category
        );

        assertEquals("PRD-9001", product.getProductCode());
        assertEquals("Laptop Dock", product.getName());
        assertEquals("LD-9001", product.getSku());
        assertEquals(BigDecimal.valueOf(399.00), product.getSellingPrice());
        assertTrue(product.isActive());
        assertEquals(category, product.getCategory());
        assertEquals(10, product.getReorderQuantity());
    }

    @Test
    void categoryDtoDefaultsRemainValid() {
        var dto = new com.enterprisepro.erp.dto.CategoryDto();
        dto.setName("Accessories");
        dto.setCode("ACC");
        dto.setDescription("Peripherals");

        assertTrue(dto.isActive());
        assertEquals("Accessories", dto.getName());
        assertEquals("ACC", dto.getCode());
        assertEquals("Peripherals", dto.getDescription());
    }

    @Test
    void generatedAnalyticsServiceProducesStableProjection() {
        var service = new com.enterprisepro.erp.analytics.FinancialForecastService();
        var projection = service.buildProjection1("revenue", BigDecimal.valueOf(1000), 4);

        assertEquals(4, projection.size());
        assertTrue(projection.containsKey("revenue-1"));
        assertFalse(projection.isEmpty());
        assertEquals(10, service.getSignalLabels90().size());
        assertTrue(service.isStable(BigDecimal.valueOf(2.5), BigDecimal.valueOf(3.0)));
    }
}
