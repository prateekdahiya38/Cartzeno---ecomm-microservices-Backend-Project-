package com.productservice.services;

import com.productservice.dtos.CategoryRequestDto;
import com.productservice.dtos.CategoryResponseDto;

import java.util.UUID;

public interface CategoryService {
    CategoryResponseDto getcategory(UUID id);
    CategoryResponseDto addCategory(CategoryRequestDto categoryRequestDto);
}
