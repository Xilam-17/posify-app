package com.posify.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemsResponseDto {
    private Long id;
    private Integer quantity;
    private Double unitPrice;
    private Double subtotal;
    private ProductDto productDetails;
}