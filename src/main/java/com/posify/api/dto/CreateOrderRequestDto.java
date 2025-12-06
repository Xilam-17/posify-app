package com.posify.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CreateOrderRequestDto {
    private Long tableId;
    private List<OrderItemDto> items;
}
