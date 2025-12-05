package com.posify.api.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.posify.api.dto.ProductDto;
import com.posify.api.service.ProductService;

@RestController
@RequestMapping("/api/v1/menu")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/category/{categoryId}/product/create")
    public ResponseEntity<ProductDto> createProduct(@PathVariable Long categoryId, @RequestBody ProductDto productDto) {
        return new ResponseEntity<>(productService.createProduct(categoryId, productDto), HttpStatus.CREATED);
    }

    @GetMapping("/product/list")
    public ResponseEntity<List<ProductDto>> getProductList(@RequestParam(required = false) Long categoryId) {

        List<ProductDto> products;

        if (categoryId != null) {
            products = productService.getProductsByCategory(categoryId);
        } else {
            products = productService.getAllProducts();
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/category/{categoryId}/product/{productId}/list")
    public ResponseEntity<ProductDto> getProductById(
            @PathVariable Long categoryId,
            @PathVariable Long productId
    ) {
        return ResponseEntity.ok(productService.getProductById(categoryId, productId));
    }

    @PutMapping("/category/{categoryId}/product/{productId}/update")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long categoryId, @PathVariable Long productId,
                                                    @RequestBody ProductDto productDto) {

        ProductDto updated = productService.updateProduct(categoryId, productId, productDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/category/{categoryId}/product/{productId}/delete")
    public ResponseEntity<String> deleteProduct(@PathVariable Long categoryId, @PathVariable Long productId) {

        productService.deleteProduct(categoryId, productId);
        return ResponseEntity.ok("Product deleted successfully.");
    }
}