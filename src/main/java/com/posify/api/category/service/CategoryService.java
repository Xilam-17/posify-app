package com.posify.api.category.service;

import java.util.List;

import com.posify.api.category.entity.Category;
import com.posify.api.category.repository.CategoryRepository;
import com.posify.api.category.request.CategoryRequest;
import com.posify.api.category.response.CategoryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    private void isAlreadyExist(String name) {
        categoryRepository.findByName(name)
                .ifPresent(c -> {
                    throw new RuntimeException("Category already exist by name: " + name);
                });
    }

    private Category findById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Category not found by ID :" + id));
        return category;
    }

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        isAlreadyExist(categoryRequest.getName());
        Category category = Category.mapToEntity(categoryRequest);
        Category savedCategory = categoryRepository.save(category);
        return CategoryResponse.mapToDto(savedCategory);
    }

    @Override
    public List<CategoryResponse> getCategoryList() {

        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(CategoryResponse::mapToDto)
                .toList();
    }

    @Override
    public CategoryResponse getCategoryById(Long id) {
        Category category = findById(id);
        return CategoryResponse.mapToDto(category);
    }

    @Override
    public CategoryResponse updateCategory(Long id, CategoryRequest categoryRequest) {
        Category category = findById(id);
        category.setName(categoryRequest.getName());
        Category saved = categoryRepository.save(category);
        return CategoryResponse.mapToDto(saved);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = findById(id);
        categoryRepository.delete(category);
    }

}