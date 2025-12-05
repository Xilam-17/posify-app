package com.posify.api.service;

import java.util.List;

import com.posify.api.dto.CategoryDto;

public interface CategoryService {

	CategoryDto createCategory(CategoryDto categoryDto);

	List<CategoryDto> getCategoryList();

	CategoryDto getCategoryById(Long id);

	CategoryDto updateCategory(Long id, CategoryDto categoryDto);

	void deleteCategory(Long id);
	
	

}
