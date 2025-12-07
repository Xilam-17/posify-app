package com.posify.api.table.service;

import com.posify.api.table.response.TableDto;
import com.posify.api.table.entity.TableStatus;
import com.posify.api.table.entity.Table;
import com.posify.api.table.repository.TableRepository;
import com.posify.api.mappers.TableMappers;
import com.posify.api.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ITableService implements TableService {

    private final TableRepository tableRepository;
    private final ProductRepository productRepository;

    public ITableService(TableRepository tableRepository, ProductRepository productRepository) {
        this.tableRepository = tableRepository;
        this.productRepository = productRepository;
    }

    @Override
    public TableDto createTable(TableDto tableDto) {
        Table table = TableMappers.mapToEntity(tableDto);
        Table savedTable = tableRepository.save(table);
        return TableMappers.mapToDto(savedTable);
    }

    @Override
    public List<TableDto> getTables() {
        List<Table> tables = tableRepository.findAll();
        return tables.stream()
                .map(TableMappers::mapToDto)
                .toList();
    }

    @Override
    public TableDto updateTable(Long id, TableDto tableDto) {
        Table table = tableRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Table not found with id: " + id));

        table.setTableNumber(tableDto.getTableNumber());

        if (tableDto.getStatus() != null) {
            table.setStatus(TableStatus.valueOf(tableDto.getStatus()));
        }

        Table savedTable = tableRepository.save(table);
        return TableMappers.mapToDto(savedTable);
    }

    @Override
    public void deleteTable(Long id) {
        Table table = tableRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Table not found with id: " + id));
        tableRepository.delete(table);
    }
}