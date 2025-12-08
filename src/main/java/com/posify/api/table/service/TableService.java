package com.posify.api.table.service;

import com.posify.api.table.request.TableRequest;
import com.posify.api.table.response.TableResponse;
import com.posify.api.table.entity.Table;
import com.posify.api.table.repository.TableRepository;
import com.posify.api.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableService implements ITableService {

    private final TableRepository tableRepository;
    private final ProductRepository productRepository;

    public TableService(TableRepository tableRepository, ProductRepository productRepository) {
        this.tableRepository = tableRepository;
        this.productRepository = productRepository;
    }

    @Override
    public TableResponse createTable(TableRequest request) {
        Table table = Table.mapToEntity(request);
        Table savedTable = tableRepository.save(table);
        return TableResponse.mapToDto(savedTable);
    }

    @Override
    public List<TableResponse> getTables() {
        List<Table> tables = tableRepository.findAll();
        return tables.stream()
                .map(TableResponse::mapToDto)
                .toList();
    }

    @Override
    public TableResponse updateTable(Long id, TableRequest request) {
        Table table = findById(id);
        table.setTableNumber(request.getTableNumber());
        Table updatedTable = tableRepository.save(table);
        return TableResponse.mapToDto(updatedTable);
    }

    @Override
    public void deleteTable(Long id) {
        Table table = findById(id);
        tableRepository.delete(table);
    }

    private Table findById(Long id) {
        Table table = tableRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Table not found with ID : " + id));
        return table;
    }

}