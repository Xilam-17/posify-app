package com.posify.api.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductDto {
    private Long productId;
    private String productName;
    private Double price;
    private Long categoryId;
    private String categoryName;

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

}