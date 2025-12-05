package com.posify.api.service;

import java.util.List;
import com.posify.api.dto.ProductDto;

public interface ProductService {

	ProductDto createProduct(Long categoryId, ProductDto productDto);

	List<ProductDto> getProductsByCategory(Long categoryId);
	
	List<ProductDto> getAllProducts();

	ProductDto getProductById(Long categoryId, Long productId);

	ProductDto updateProduct(Long categoryId, Long productId, ProductDto productDto);

	void deleteProduct(Long categoryId, Long productId);


    List<ProductDto> searchProducts(String keyword);
}