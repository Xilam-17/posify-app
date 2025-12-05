package com.posify.api.serviceImpl;

import java.util.ArrayList; // Need to explicitly import ArrayList now
import java.util.List;
import org.springframework.stereotype.Service;

import com.posify.api.dto.ProductDto;
import com.posify.api.entity.Category;
import com.posify.api.entity.Product;
import com.posify.api.mappers.ProductMappers;
import com.posify.api.repository.CategoryRepository;
import com.posify.api.repository.ProductRepository;
import com.posify.api.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
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

    @Override
    public ProductDto createProduct(Long categoryId, ProductDto productDto) {
        Category category = getCategory(categoryId);

        Product product = ProductMappers.mapToEntity(productDto);
        product.setCategory(category);

        Product saved = productRepository.save(product);
        return ProductMappers.mapToDto(saved);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> dtos = new ArrayList<>();
        for (Product product : products) {
            dtos.add(ProductMappers.mapToDto(product));
        }
        return dtos;
    }

    @Override
    public List<ProductDto> getProductsByCategory(Long categoryId) {
        List<Product> products = productRepository.findByCategory_Id(categoryId);
        List<ProductDto> dtos = new ArrayList<>();
        for (Product product : products) {
            dtos.add(ProductMappers.mapToDto(product));
        }
        return dtos;
    }

    @Override
    public ProductDto getProductById(Long categoryId, Long productId) {
        Product product = getProduct(productId);
        validateCategoryOwnership(categoryId, product);
        return ProductMappers.mapToDto(product);
    }

    @Override
    public ProductDto updateProduct(Long categoryId, Long productId, ProductDto productDto) {
        Category category = getCategory(categoryId);
        Product product = getProduct(productId);
        validateCategoryOwnership(categoryId, product);

        product.setProductName(productDto.getProductName());
        product.setPrice(productDto.getPrice());
        product.setCategory(category);

        Product updated = productRepository.save(product);
        return ProductMappers.mapToDto(updated);
    }

    @Override
    public void deleteProduct(Long categoryId, Long productId) {
        Product product = getProduct(productId);
        validateCategoryOwnership(categoryId, product);

        productRepository.delete(product);
    }

    @Override
    public List<ProductDto> searchProducts(String keyword) {
        List<Product> products = productRepository.findByProductNameContainingIgnoreCase(keyword);

        List<ProductDto> dtos = new ArrayList<>();

        for (Product product : products) {
            dtos.add(ProductMappers.mapToDto(product));
        }
        return dtos;
    }
}