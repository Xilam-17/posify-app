package com.posify.api.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderResponseDto {
    private Long id;
    private LocalDateTime orderTime;
    private Double totalAmount;
    private String status;

    private List<OrderItemsResponseDto> items;
}