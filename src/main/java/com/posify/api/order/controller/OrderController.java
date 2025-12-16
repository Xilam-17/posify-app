package com.posify.api.order.controller;

import com.posify.api.order.request.OrderRequest;
import com.posify.api.order.response.OrderResponse;
import com.posify.api.order.service.IOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    private final IOrderService service;

    public OrderController(IOrderService service) {
        this.service = service;
    }

    @PostMapping("/table/{id}/order/save-order")
    public ResponseEntity<OrderResponse> saveOrder(@PathVariable Long id, @RequestBody OrderRequest request) {
        request.setTableId(id);
        return new ResponseEntity<>(service.saveOrder(id,request), HttpStatus.CREATED);
    }

    @GetMapping("/table/order/{orderId}/check-out")
    public ResponseEntity<OrderResponse> checkOutOrder(@PathVariable Long orderId) {
        OrderResponse response = service.checkOutOrder(orderId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}