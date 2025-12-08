package com.posify.api.product.service;

import java.util.List;

import com.posify.api.product.request.ProductRequest;
import com.posify.api.product.entity.Product;
import com.posify.api.product.repository.ProductRepository;
import com.posify.api.product.response.ProductResponse;
import org.springframework.stereotype.Service;

import com.posify.api.category.entity.Category;
import com.posify.api.category.repository.CategoryRepository;

@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    private Category getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));
    }

    private Product getProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
    }

    private void validateCategoryOwnership(Long categoryId, Product product) {
        if (!product.getCategory().getId().equals(categoryId)) {
            throw new RuntimeException("Product not found under category with id: " + categoryId);
        }
    }

    private void isAlreadyExist(String productName) {
        productRepository.findByProductName(productName)
                .ifPresent(p -> {
                    throw new RuntimeException("Product already exists with name: " + productName);
                });
    }

    @Override
    public ProductResponse createProduct(Long categoryId, ProductRequest productRequest) {
        isAlreadyExist(productRequest.getProductName());
        Category category = getCategory(categoryId);
        Product product = new Product();

        product.setProductName(productRequest.getProductName());
        product.setPrice(productRequest.getPrice());
        product.setImgId(productRequest.getImgId());
        product.setDescription(productRequest.getDescription());
        product.setCategory(category);

        Product savedProduct = productRepository.save(product);
        return ProductResponse.mapToDto(savedProduct);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(ProductResponse::mapToDto)
                .toList();
    }

    @Override
    public List<ProductResponse> getProductsByCategory(Long categoryId) {
        List<Product> products = productRepository.findByCategory_Id(categoryId);
              return products.stream()
                .map(ProductResponse::mapToDto)
                .toList();
    }

    @Override
    public ProductResponse getProductById(Long categoryId, Long productId) {
        Category category = getCategory(categoryId);
        Product product = getProduct(productId);
        validateCategoryOwnership(categoryId, product);
        return ProductResponse.mapToDto(product);
    }

    @Override
    public List<ProductResponse> searchProducts(String keyword) {
        return productRepository.findByProductNameContainingIgnoreCase(keyword).stream()
                .map(ProductResponse::mapToDto)
                .toList();
    }

    @Override
    public ProductResponse updateProduct(Long categoryId, Long productId, ProductRequest productRequest) {
        isAlreadyExist(productRequest.getProductName());
        Category category = getCategory(categoryId);
        Product product = getProduct(productId);
        validateCategoryOwnership(categoryId, product);

        product.setProductName(productRequest.getProductName());
        product.setPrice(productRequest.getPrice());
        product.setImgId(productRequest.getImgId());
        product.setDescription(productRequest.getDescription());

        Product updated = productRepository.save(product);
        return ProductResponse.mapToDto(updated);
    }

    @Override
    public void deleteProduct(Long categoryId, Long productId) {
        Product product = getProduct(productId);
        validateCategoryOwnership(categoryId, product);
        productRepository.delete(product);
    }

}