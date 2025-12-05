package com.posify.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.posify.api.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
