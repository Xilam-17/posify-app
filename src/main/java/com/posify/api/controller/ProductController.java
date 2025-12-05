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
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/product/{productId}/list")
    public ResponseEntity<ProductDto> getProductById(
            @PathVariable Long categoryId,
            @PathVariable Long productId
    ) {
        return new ResponseEntity<>(productService.getProductById(categoryId, productId), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDto>> searchProducts(
            @RequestParam(name = "keyword", required = false) String keyword) {

        if (keyword == null || keyword.isEmpty()) {
            return new ResponseEntity<>(List.of(), HttpStatus.OK);
        }

        List<ProductDto> products = productService.searchProducts(keyword);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping("/category/{categoryId}/product/{productId}/update")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long categoryId, @PathVariable Long productId,
                                                    @RequestBody ProductDto productDto) {

        ProductDto updated = productService.updateProduct(categoryId, productId, productDto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/category/{categoryId}/product/{productId}/delete")
    public ResponseEntity<String> deleteProduct(@PathVariable Long categoryId, @PathVariable Long productId) {

        productService.deleteProduct(categoryId, productId);
        return new ResponseEntity<>("Product deleted successfully.", HttpStatus.OK);
    }
}