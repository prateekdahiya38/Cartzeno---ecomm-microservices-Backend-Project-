package com.productservice.mappers;

import com.productservice.dtos.CategoryRequestDto;
import com.productservice.dtos.CategoryResponseDto;
import com.productservice.modals.Category;

public class CategoryMapper {

    public static CategoryResponseDto toCategoryResponseDto(Category category) {
        return new CategoryResponseDto(category.getId(), category.getName(), category.getDescription());
    }

    public static Category toCategory(CategoryRequestDto categoryRequestDto) {
        Category category = new Category();
        category.setName(categoryRequestDto.name());
        category.setDescription(categoryRequestDto.description());
        return category;
    }
}
