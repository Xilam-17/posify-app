package com.posify.api.product.service;

import com.posify.api.product.request.ProductRequest;
import com.posify.api.product.response.ProductResponse;

import java.util.List;

public interface IProductService {

	ProductResponse createProduct(Long categoryId, ProductRequest productRequest);

	List<ProductResponse> getProductsByCategory(Long categoryId);
	
	List<ProductResponse> getAllProducts();

	ProductResponse getProductById(Long categoryId, Long productId);

	ProductResponse updateProduct(Long categoryId, Long productId, ProductRequest productRequest);

	void deleteProduct(Long categoryId, Long productId);

    List<ProductResponse> searchProducts(String keyword);
}