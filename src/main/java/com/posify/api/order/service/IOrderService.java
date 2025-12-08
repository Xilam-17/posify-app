package com.posify.api.order.service;

import com.posify.api.order.repository.OrderRepository;
import com.posify.api.product.repository.ProductRepository;
import com.posify.api.table.repository.TableRepository;
import org.springframework.stereotype.Service;

@Service
public class IOrderService implements OrderService {

    private final TableRepository tableRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public IOrderService(TableRepository tableRepository, ProductRepository productRepository, OrderRepository orderRepository) {
        this.tableRepository = tableRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

}