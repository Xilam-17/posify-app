package com.posify.api.mappers;

import com.posify.api.dto.ProductDto;
import com.posify.api.entity.Product;

public class ProductMappers {

    public static ProductDto mapToDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setCategoryName(product.getCategory().getName());
        dto.setProductName(product.getProductName());
        dto.setPrice(product.getPrice());
        return dto;
    }

    public static Product mapToEntity(ProductDto dto) {
        Product product = new Product();
        product.setProductName(dto.getProductName());
        product.setPrice(dto.getPrice());
        return product;
    }
}
