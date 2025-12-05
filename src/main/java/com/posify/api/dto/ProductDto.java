package com.posify.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private Long id;
    private String productName;
    private Double price;
    private Long categoryId;
    private String categoryName;
}