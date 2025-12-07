package com.posify.api.product.response;

import com.posify.api.product.entity.Product;
import com.posify.api.product.request.ProductRequest;
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
    private Long imgId;

    public static ProductResponse mapToDto(Product product) {
        ProductResponse dto = new ProductResponse();
        dto.setProductName(product.getProductName());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());
        dto.setCategoryName(product.getCategory().getName());
        dto.setImgId(product.getImgId());
        return dto;
    }
}
