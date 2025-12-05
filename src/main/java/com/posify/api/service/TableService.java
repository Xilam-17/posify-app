package com.posify.api.service;

import com.posify.api.dto.TableDto;

import java.util.List;

public interface TableService {

    TableDto createTable(TableDto tableDto);

    List<TableDto> getTables();

    TableDto updateTable(Long id, TableDto tableDto);

    void deleteTable(Long id);
}
