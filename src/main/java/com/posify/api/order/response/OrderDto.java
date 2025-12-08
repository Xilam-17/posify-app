package com.posify.api.order.response;

import com.posify.api.order.request.OrderItemDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private Double totalAmount;
    private String status;
    private Long tableId;
    private List<OrderItemDto> items;
}