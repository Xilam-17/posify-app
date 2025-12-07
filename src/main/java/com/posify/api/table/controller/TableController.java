package com.posify.api.table.controller;

import com.posify.api.product.service.IProductService;
import com.posify.api.table.response.TableDto;
import com.posify.api.table.service.TableService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/home")
public class TableController {

    private final TableService tableService;
    private final IProductService IProductService;

    public TableController(TableService tableService, IProductService IProductService) {
        this.tableService = tableService;
        this.IProductService = IProductService;
    }

    @PostMapping("/table/create")
    public ResponseEntity<TableDto> createTable(@RequestBody TableDto tableDto) {
        return new ResponseEntity<>(tableService.createTable(tableDto), HttpStatus.CREATED);
    }

    @GetMapping("/table/list")
    public ResponseEntity<List<TableDto>> getTables() {
        return new ResponseEntity<>(tableService.getTables(), HttpStatus.OK);
    }

    @PutMapping("/table/{id}/update")
    public ResponseEntity<TableDto> updateTable(@PathVariable Long id, @RequestBody TableDto tableDto) {
        return new ResponseEntity<>(tableService.updateTable(id, tableDto), HttpStatus.OK);
    }

    @DeleteMapping("/table/{id}/delete")
    public ResponseEntity<String> deleteTable(@PathVariable Long id) {
        tableService.deleteTable(id);
        return new ResponseEntity<>("Table deleted successfully!", HttpStatus.OK);
    }
}