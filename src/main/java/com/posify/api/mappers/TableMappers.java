package com.posify.api.mappers;

import com.posify.api.dto.TableDto;
import com.posify.api.entity.Table;
import com.posify.api.entity.TableStatus;
import com.posify.api.dto.OrderDto;
import com.posify.api.entity.Order;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TableMappers {

    public static TableDto mapToDto(Table table) {
        TableDto dto = new TableDto();
        dto.setId(table.getId());
        dto.setTableNumber(table.getTableNumber());
        dto.setStatus(table.getStatus().name());

        if (table.getOrders() != null) {
            List<OrderDto> orderDtos = table.getOrders().stream()
                    .map(OrderMappers::mapToDto)
                    .collect(Collectors.toList());

            dto.setOrders(orderDtos);
        } else {
            dto.setOrders(Collections.emptyList());
        }

        return dto;
    }

    public static Table mapToEntity(TableDto tableDto) {
        Table table = new Table();
        table.setId(tableDto.getId());
        table.setTableNumber(tableDto.getTableNumber());
        table.setStatus(TableStatus.valueOf(tableDto.getStatus()));

        if (tableDto.getOrders() != null) {
            List<Order> orders = tableDto.getOrders().stream()
                    .map(OrderMappers::mapToEntity)
                    .collect(Collectors.toList());

            orders.forEach(order -> order.setTable(table));

            table.setOrders(orders);
        } else {
            table.setOrders(Collections.emptyList());
        }

        return table;
    }
}