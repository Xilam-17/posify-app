package com.posify.api.table.service;

import com.posify.api.table.request.TableRequest;
import com.posify.api.table.response.TableResponse;

import java.util.List;

public interface ITableService {

    TableResponse createTable(TableRequest request);

    List<TableResponse> getTables();

    TableResponse updateTable(Long id, TableRequest request);

    void deleteTable(Long id);
}
