package com.posify.api.category.service;

import com.posify.api.category.request.CategoryRequest;
import com.posify.api.category.response.CategoryResponse;

import java.util.List;

public interface ICategoryService {

	CategoryResponse createCategory(CategoryRequest categoryRequest);

	List<CategoryResponse> getCategoryList();

	CategoryResponse getCategoryById(Long id);

	CategoryResponse updateCategory(Long id, CategoryRequest categoryRequest);

	void deleteCategory(Long id);
	
	

}
