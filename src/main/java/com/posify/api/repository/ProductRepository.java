package com.posify.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.posify.api.entity.Category;
import com.posify.api.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory_Id(Long categoryId);

    List<Product> findByProductNameContainingIgnoreCase(String keyword);
}
