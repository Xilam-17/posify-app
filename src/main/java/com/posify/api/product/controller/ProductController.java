package com.posify.api.product.controller;

import java.util.List;

import com.posify.api.product.request.ProductRequest;
import com.posify.api.product.response.ProductResponse;
import com.posify.api.product.service.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/menu")
public class ProductController {

    private final IProductService service;

    public ProductController(IProductService service) {
        this.service = service;
    }

    @PostMapping("/category/{categoryId}/product/create")
    public ResponseEntity<ProductResponse> createProduct(
            @PathVariable Long categoryId,
            @RequestBody ProductRequest productRequest) {
        ProductResponse created = service.createProduct(categoryId, productRequest);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/product/list")
    public ResponseEntity<List<ProductResponse>> getProductList(@RequestParam(required = false) Long categoryId) {
        List<ProductResponse> products;
        if(categoryId != null) {
            products = service.getProductsByCategory(categoryId);
        } else {
            products = service.getAllProducts();
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/product/{productId}/product-details")
    public ResponseEntity<ProductResponse> getProductById(
            @PathVariable Long categoryId,
            @PathVariable Long productId) {
        ProductResponse products = service.getProductById(categoryId, productId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> searchProducts(
            @RequestParam(name = "keyword", required = false) String keyword) {

        if (keyword == null || keyword.isEmpty()) {
            return new ResponseEntity<>(List.of(), HttpStatus.OK);
        }

        List<ProductResponse> products = service.searchProducts(keyword);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping("/category/{categoryId}/product/{productId}/update")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long categoryId,
            @PathVariable Long productId,
            @RequestBody ProductRequest productRequest) {
        ProductResponse updated = service.updateProduct(categoryId, productId, productRequest);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/category/{categoryId}/product/{productId}/delete")
    public ResponseEntity<String> deleteProduct(@PathVariable Long categoryId, @PathVariable Long productId) {

        service.deleteProduct(categoryId, productId);
        return new ResponseEntity<>("Product deleted successfully.", HttpStatus.OK);
    }
}