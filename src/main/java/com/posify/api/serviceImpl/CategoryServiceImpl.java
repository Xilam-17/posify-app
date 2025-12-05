package com.posify.api.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.posify.api.dto.CategoryDto;
import com.posify.api.entity.Category;
import com.posify.api.mappers.CategoryMappers;
import com.posify.api.repository.CategoryRepository;
import com.posify.api.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;

	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {

		Category category = CategoryMappers.mapToEntity(categoryDto);

		Category savedCategory = categoryRepository.save(category);

		return CategoryMappers.mapToDto(savedCategory);
	}

	@Override
	public List<CategoryDto> getCategoryList() {

		List<Category> categories = categoryRepository.findAll();
		List<CategoryDto> dtos = new ArrayList<>();

		for (Category category : categories) {
			CategoryDto dto = CategoryMappers.mapToDto(category);
			dtos.add(dto);
		}

		return dtos;
	}

	@Override
	public CategoryDto getCategoryById(Long id) {
		Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException());
		return CategoryMappers.mapToDto(category);
	}

	@Override
	public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {

		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Category not found"));

		category.setName(categoryDto.getName());

		Category saved = categoryRepository.save(category);
		return CategoryMappers.mapToDto(saved);
	}

	@Override
	public void deleteCategory(Long id) {
		Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException());
		categoryRepository.delete(category);

	}

}
