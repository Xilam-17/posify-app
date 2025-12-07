package com.posify.api.table.service;

import com.posify.api.table.response.TableDto;

import java.util.List;

public interface TableService {

    TableDto createTable(TableDto tableDto);

    List<TableDto> getTables();

    TableDto updateTable(Long id, TableDto tableDto);

    void deleteTable(Long id);
}
