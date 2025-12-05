package com.posify.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemsResponseDto {
    private Long id;
    private Integer quantity;
    private Double unitPrice;      // Price at the time of order
    private Double subtotal;       // quantity * unitPrice

    // Details of the product ordered (often a simple DTO subset)
    private ProductDto productDetails;
}