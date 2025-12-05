package com.posify.api.mappers;

import com.posify.api.dto.TableDto;
import com.posify.api.entity.Table;
import com.posify.api.entity.TableStatus;

import java.util.Collections;
import java.util.stream.Collectors;

public class TableMappers {

    public static TableDto mapToDto(Table table) {
        TableDto dto = new TableDto();
        dto.setId(table.getId());
        dto.setTableNumber(table.getTableNumber());
        dto.setStatus(table.getStatus().name());

        if (table.getProducts() != null) {
            dto.setProducts(
                    table.getProducts()
                            .stream()
                            .map(ProductMappers::mapToDto)
                            .collect(Collectors.toList())
            );
        } else {
            dto.setProducts(Collections.emptyList());
        }

        return dto;
    }

    public static Table mapToEntity(TableDto tableDto) {
        Table table = new Table();
        table.setId(tableDto.getId());
        table.setTableNumber(tableDto.getTableNumber());
        table.setStatus(TableStatus.valueOf(tableDto.getStatus()));

        if (tableDto.getProducts() != null) {
            table.setProducts(
                    tableDto.getProducts()
                            .stream()
                            .map(ProductMappers::mapToEntity)
                            .collect(Collectors.toList())
            );
        } else {
            table.setProducts(Collections.emptyList());
        }

        return table;
    }
}