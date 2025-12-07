package com.posify.api.order.repository;

import com.posify.api.order.entity.Order;
import com.posify.api.order.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByTableIdAndStatus(Long tableId, OrderStatus status);
}
