package com.posify.api.controller;

import com.posify.api.dto.CreateOrderRequestDto;
import com.posify.api.dto.OrderDto;
import com.posify.api.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/table/order/create")
    public ResponseEntity<OrderDto> createOrder(@RequestBody CreateOrderRequestDto requestDto) {
        OrderDto createdOrder = orderService.createNewOrder(requestDto);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @PostMapping("/table/order/{orderId}/checkout")
    public ResponseEntity<OrderDto> checkoutOrder(@PathVariable Long orderId) {
        OrderDto paidOrder = orderService.checkoutOrder(orderId);
        return new ResponseEntity<>(paidOrder, HttpStatus.OK);
    }
}