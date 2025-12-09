package com.posify.api.order.service;

import com.posify.api.order.entity.Order;
import com.posify.api.order.entity.OrderItem;
import com.posify.api.order.entity.OrderStatus;
import com.posify.api.order.request.OrderItemRequest;
import com.posify.api.order.request.OrderRequest;
import com.posify.api.order.response.OrderResponse;
import com.posify.api.product.entity.Product;
import com.posify.api.table.entity.Table;
import com.posify.api.order.repository.OrderRepository;
import com.posify.api.order.repository.OrderItemRepository;
import com.posify.api.product.repository.ProductRepository;
import com.posify.api.table.repository.TableRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final TableRepository tableRepository;
    private final ProductRepository productRepository;

    public OrderService(
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository,
            TableRepository tableRepository,
            ProductRepository productRepository) {

        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.tableRepository = tableRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public OrderResponse saveOrder(Long id, OrderRequest request) {
        Table table = tableRepository.findById(request.getTableId())
                .orElseThrow(() -> new IllegalArgumentException("Table not found!"));

        Order order = new Order();
        order.setTable(table);
        order.setOrderItems(new ArrayList<>());
        order.setFinalTotalAmount(0.0);

        double runningTotal = 0.0;
        List<OrderItem> itemsToSave = new ArrayList<>();

        for (OrderItemRequest itemRequest : request.getItems()) {
            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found with ID : " + itemRequest.getProductId()));

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setOrder(order);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setPriceAtOrder(product.getPrice());

            double subtotal = orderItem.calculateSubtotal();
            orderItem.setTotalAmount(subtotal);
            runningTotal += subtotal;

            itemsToSave.add(orderItem);
        }

        order.setFinalTotalAmount(runningTotal);
        Order savedOrder = orderRepository.save(order);

        itemsToSave.forEach(item -> item.setOrder(savedOrder));

        orderItemRepository.saveAll(itemsToSave);
        savedOrder.setOrderItems(itemsToSave);

        return OrderResponse.mapToDto(savedOrder);
    }

    @Override
    @Transactional
    public OrderResponse checkOutOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));

        if (order.getStatus() != OrderStatus.OPEN) {
            throw new IllegalStateException("Order ID " + orderId + " is already " + order.getStatus());
        }

        order.setStatus(OrderStatus.PAID);
        Order checkedOutOrder = orderRepository.save(order);
        return OrderResponse.mapToDto(checkedOutOrder);
    }
}
