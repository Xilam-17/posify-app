package com.posify.api.mappers;

import com.posify.api.dto.OrderDto;
import com.posify.api.dto.OrderItemDto;
import com.posify.api.entity.Order;
import com.posify.api.entity.OrderStatus;
import com.posify.api.entity.Table;
import com.posify.api.entity.OrderItem;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class OrderMappers {

    public static OrderDto mapToDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setStatus(order.getStatus().name());
        dto.setOrderTime(order.getOrderTime());

        if (order.getTable() != null) {
            dto.setTableId(order.getTable().getId());
        }

        if (order.getItems() != null) {
            List<OrderItemDto> itemDtos = order.getItems().stream()
                    .map(OrderItemMappers::mapToDto)
                    .collect(Collectors.toList());
            dto.setItems(itemDtos);
        } else {
            dto.setItems(Collections.emptyList());
        }

        return dto;
    }

    public static Order mapToEntity(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setTotalAmount(orderDto.getTotalAmount());
        order.setOrderTime(orderDto.getOrderTime());

        if (orderDto.getStatus() != null) {
            order.setStatus(OrderStatus.valueOf(orderDto.getStatus()));
        }

        if (orderDto.getTableId() != null) {
            Table table = new Table();
            table.setId(orderDto.getTableId());
            order.setTable(table);
        }

        if (orderDto.getItems() != null) {
            List<OrderItem> items = orderDto.getItems().stream()
                    .map(OrderItemMappers::mapToEntity)
                    .collect(Collectors.toList());

            items.forEach(item -> item.setOrder(order));

            order.setItems(items);
        } else {
            order.setItems(Collections.emptyList());
        }

        return order;
    }
}