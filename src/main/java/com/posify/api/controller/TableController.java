package com.posify.api.controller;

import com.posify.api.dto.TableDto;
import com.posify.api.service.ProductService;
import com.posify.api.service.TableService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/home")
public class TableController {

    private final TableService tableService;
    private final ProductService productService;

    public TableController(TableService tableService, ProductService productService) {
        this.tableService = tableService;
        this.productService = productService;
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