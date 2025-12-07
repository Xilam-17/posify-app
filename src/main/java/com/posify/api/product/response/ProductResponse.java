package com.posify.api.product.response;

import com.posify.api.product.entity.Product;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductResponse {
    private Long productId;
    private String productName;
    private Double price;
    private String categoryName;
    private String description;

    public static ProductResponse mapToDto(Product product) {
        ProductResponse dto = new ProductResponse();
        dto.setProductId(product.getProductId());
        dto.setProductName(product.getProductName());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());
        dto.setCategoryName(product.getCategory().getName());
        return dto;
    }
}
