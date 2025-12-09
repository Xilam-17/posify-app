package com.posify.api.category.response;

import com.posify.api.category.entity.Category;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryResponse {
    private Long id;
    private String name;

    public static CategoryResponse mapToDto(Category category) {
        CategoryResponse dto = new CategoryResponse();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }
}
