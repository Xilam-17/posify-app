package com.posify.api.serviceImpl;

import com.posify.api.dto.CreateOrderRequestDto;
import com.posify.api.dto.OrderDto;
import com.posify.api.entity.*;
import com.posify.api.mappers.OrderMappers;
import com.posify.api.repository.*;
import com.posify.api.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final TableRepository tableRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderServiceImpl(TableRepository tableRepository, ProductRepository productRepository, OrderRepository orderRepository) {
        this.tableRepository = tableRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional
    public OrderDto createNewOrder(CreateOrderRequestDto requestDto) {
        Table table = tableRepository.findById(requestDto.getTableId())
                .orElseThrow(() -> new RuntimeException("Table not found"));

        if (table.getStatus() != TableStatus.FREE && table.getStatus() != TableStatus.OCCUPIED) {
            throw new RuntimeException("Table is not available for a new order.");
        }

        Order order = new Order();
        order.setTable(table);
        order.setStatus(OrderStatus.OPEN);

        List<OrderItem> orderItems = new ArrayList<>();
        double totalAmount = 0.0;

        for (var itemDto : requestDto.getItems()) {
            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found: " + itemDto.getProductId()));

            OrderItem item = new OrderItem();
            item.setProduct(product);
            item.setQuantity(itemDto.getQuantity());
            item.setPriceAtOrder(product.getPrice());
            item.setOrder(order);

            orderItems.add(item);
            totalAmount += item.calculateSubtotal();
        }

        order.setItems(orderItems);
        order.setTotalAmount(totalAmount);

        if(table.getStatus() == TableStatus.FREE) {
            table.setStatus(TableStatus.OCCUPIED);
        }

        Order savedOrder = orderRepository.save(order);
        return OrderMappers.mapToDto(savedOrder);
    }

    @Override
    @Transactional
    public OrderDto checkoutOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(OrderStatus.PAID);
        Table table = order.getTable();
        if (table != null) {
            table.setStatus(TableStatus.FREE);
        }
        Order paidOrder = orderRepository.save(order);
        return OrderMappers.mapToDto(paidOrder);
    }
}