package com.posify.api.order.service;

import com.posify.api.order.entity.Order;
import com.posify.api.order.entity.OrderItem;
import com.posify.api.order.request.OrderItemRequest;
import com.posify.api.order.request.OrderRequest;
import com.posify.api.order.response.OrderResponse;
import com.posify.api.product.entity.Product;
import com.posify.api.table.entity.Table;
import com.posify.api.order.repository.OrderRepository;
import com.posify.api.order.repository.OrderItemRepository;
import com.posify.api.product.repository.ProductRepository;
import com.posify.api.table.repository.TableRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
                    .orElseThrow(() -> new IllegalArgumentException("Product not found with ID : " + itemRequest.getProductId()));

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
    }
