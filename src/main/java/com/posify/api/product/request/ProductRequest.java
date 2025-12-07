package com.posify.api.product.request;

import com.posify.api.category.entity.Category;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductRequest {
    private String productName;
    @NotNull
    private Double price;
    private Long imgId;
    private String description;
}