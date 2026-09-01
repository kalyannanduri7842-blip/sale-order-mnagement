package com.enterprisepro.erp.service.impl;

import com.enterprisepro.erp.dto.CategoryDto;
import com.enterprisepro.erp.dto.ProductDto;
import com.enterprisepro.erp.entity.Brand;
import com.enterprisepro.erp.entity.Category;
import com.enterprisepro.erp.entity.Product;
import com.enterprisepro.erp.entity.Unit;
import com.enterprisepro.erp.exception.BadRequestException;
import com.enterprisepro.erp.exception.ResourceNotFoundException;
import com.enterprisepro.erp.repository.BrandRepository;
import com.enterprisepro.erp.repository.CategoryRepository;
import com.enterprisepro.erp.repository.ProductRepository;
import com.enterprisepro.erp.repository.UnitRepository;
import com.enterprisepro.erp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private UnitRepository unitRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<ProductDto> getAllProducts(String search, Pageable pageable) {
        if (StringUtils.hasText(search)) {
            return productRepository.findByNameContainingIgnoreCaseOrSkuContainingIgnoreCaseOrProductCodeContainingIgnoreCase(
                    search, search, search, pageable).map(this::mapToDto);
        }
        return productRepository.findAll(pageable).map(this::mapToDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> getLowStockProducts() {
        return productRepository.findLowStockProducts().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        return mapToDto(product);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDto getProductBySku(String sku) {
        Product product = productRepository.findBySku(sku)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "sku", sku));
        return mapToDto(product);
    }

    @Override
    @Transactional
    public ProductDto createProduct(ProductDto dto) {
        if (productRepository.findBySku(dto.getSku()).isPresent()) {
            throw new BadRequestException("Product with SKU '" + dto.getSku() + "' already exists!");
        }

        Product product = new Product();
        product.setProductCode(StringUtils.hasText(dto.getProductCode()) ? dto.getProductCode() : "PRD-" + (1000 + productRepository.count() + 1));
        product.setName(dto.getName());
        product.setSku(dto.getSku());
        product.setBarcode(dto.getBarcode());
        product.setCostPrice(dto.getCostPrice());
        product.setSellingPrice(dto.getSellingPrice());
        product.setCurrentStock(dto.getCurrentStock());
        product.setMinStockAlert(dto.getMinStockAlert() > 0 ? dto.getMinStockAlert() : 10);
        product.setReorderQuantity(dto.getReorderQuantity() > 0 ? dto.getReorderQuantity() : 50);
        product.setImageUrl(dto.getImageUrl());
        product.setDescription(dto.getDescription());
        product.setActive(true);

        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category", "id", dto.getCategoryId()));
            product.setCategory(category);
        }

        if (dto.getBrandId() != null) {
            Brand brand = brandRepository.findById(dto.getBrandId()).orElse(null);
            product.setBrand(brand);
        }

        if (dto.getUnitId() != null) {
            Unit unit = unitRepository.findById(dto.getUnitId()).orElse(null);
            product.setUnit(unit);
        }

        Product saved = productRepository.save(product);
        return mapToDto(saved);
    }

    @Override
    @Transactional
    public ProductDto updateProduct(Long id, ProductDto dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));

        product.setName(dto.getName());
        product.setSku(dto.getSku());
        product.setBarcode(dto.getBarcode());
        product.setCostPrice(dto.getCostPrice());
        product.setSellingPrice(dto.getSellingPrice());
        product.setCurrentStock(dto.getCurrentStock());
        product.setMinStockAlert(dto.getMinStockAlert());
        product.setReorderQuantity(dto.getReorderQuantity());
        product.setImageUrl(dto.getImageUrl());
        product.setDescription(dto.getDescription());
        product.setActive(dto.isActive());

        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category", "id", dto.getCategoryId()));
            product.setCategory(category);
        }

        if (dto.getBrandId() != null) {
            Brand brand = brandRepository.findById(dto.getBrandId()).orElse(null);
            product.setBrand(brand);
        }

        if (dto.getUnitId() != null) {
            Unit unit = unitRepository.findById(dto.getUnitId()).orElse(null);
            product.setUnit(unit);
        }

        Product updated = productRepository.save(product);
        return mapToDto(updated);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        product.setActive(false);
        productRepository.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(c -> {
                    CategoryDto dto = new CategoryDto();
                    dto.setId(c.getId());
                    dto.setName(c.getName());
                    dto.setCode(c.getCode());
                    dto.setDescription(c.getDescription());
                    dto.setActive(c.isActive());
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CategoryDto createCategory(CategoryDto dto) {
        Category cat = new Category(dto.getName(), dto.getCode(), dto.getDescription());
        Category saved = categoryRepository.save(cat);
        CategoryDto res = new CategoryDto();
        res.setId(saved.getId());
        res.setName(saved.getName());
        res.setCode(saved.getCode());
        res.setDescription(saved.getDescription());
        res.setActive(saved.isActive());
        return res;
    }

    private ProductDto mapToDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setProductCode(product.getProductCode());
        dto.setName(product.getName());
        dto.setSku(product.getSku());
        dto.setBarcode(product.getBarcode());
        dto.setCostPrice(product.getCostPrice());
        dto.setSellingPrice(product.getSellingPrice());
        dto.setCurrentStock(product.getCurrentStock());
        dto.setMinStockAlert(product.getMinStockAlert());
        dto.setReorderQuantity(product.getReorderQuantity());
        dto.setImageUrl(product.getImageUrl());
        dto.setDescription(product.getDescription());
        dto.setActive(product.isActive());
        dto.setLowStock(product.isLowStock());

        if (product.getCategory() != null) {
            dto.setCategoryId(product.getCategory().getId());
            dto.setCategoryName(product.getCategory().getName());
        }

        if (product.getBrand() != null) {
            dto.setBrandId(product.getBrand().getId());
            dto.setBrandName(product.getBrand().getName());
        }

        if (product.getUnit() != null) {
            dto.setUnitId(product.getUnit().getId());
            dto.setUnitName(product.getUnit().getName());
        }

        return dto;
    }
}
