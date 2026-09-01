package com.enterprisepro.erp.service;

import com.enterprisepro.erp.dto.CategoryDto;
import com.enterprisepro.erp.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Page<ProductDto> getAllProducts(String search, Pageable pageable);
    List<ProductDto> getLowStockProducts();
    ProductDto getProductById(Long id);
    ProductDto getProductBySku(String sku);
    ProductDto createProduct(ProductDto productDto);
    ProductDto updateProduct(Long id, ProductDto productDto);
    void deleteProduct(Long id);

    List<CategoryDto> getAllCategories();
    CategoryDto createCategory(CategoryDto categoryDto);
}
