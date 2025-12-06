package com.posify.api.service;

import com.posify.api.dto.OrderDto;
import com.posify.api.dto.CreateOrderRequestDto;

public interface OrderService {
    OrderDto createNewOrder(CreateOrderRequestDto requestDto);
    OrderDto checkoutOrder(Long orderId);
}