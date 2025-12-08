package com.posify.api.table.controller;

import com.posify.api.product.service.IProductService;
import com.posify.api.table.request.TableRequest;
import com.posify.api.table.response.TableResponse;
import com.posify.api.table.service.ITableService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/home")
public class TableController {

    private final ITableService tableService;

    public TableController(ITableService tableService) {
        this.tableService = tableService;
    }

    @PostMapping("/table/create")
    public ResponseEntity<TableResponse> createTable(@RequestBody TableRequest request) {
        return new ResponseEntity<>(tableService.createTable(request), HttpStatus.CREATED);
    }

    @GetMapping("/table/list")
    public ResponseEntity<List<TableResponse>> getTables() {
        return new ResponseEntity<>(tableService.getTables(), HttpStatus.OK);
    }

    @PutMapping("/table/{id}/update")
    public ResponseEntity<TableResponse> updateTable(@PathVariable Long id, @RequestBody TableRequest request) {
        return new ResponseEntity<>(tableService.updateTable(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/table/{id}/delete")
    public ResponseEntity<String> deleteTable(@PathVariable Long id) {
        tableService.deleteTable(id);
        return new ResponseEntity<>("Table deleted successfully!", HttpStatus.OK);
    }
}