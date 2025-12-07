package com.posify.api.product.repository;

import java.util.List;

import com.posify.api.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory_Id(Long categoryId);

    List<Product> findByProductNameContainingIgnoreCase(String keyword);
}
