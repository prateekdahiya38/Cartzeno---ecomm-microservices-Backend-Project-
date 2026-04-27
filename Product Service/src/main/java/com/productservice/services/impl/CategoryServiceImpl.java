package com.productservice.services.impl;

import com.productservice.dtos.CategoryRequestDto;
import com.productservice.dtos.CategoryResponseDto;
import com.productservice.exceptions.exceptions.CategoryAlreadyPresentException;
import com.productservice.exceptions.exceptions.CategoryNotFoundException;
import com.productservice.mappers.CategoryMapper;
import com.productservice.modals.Category;
import com.productservice.repositories.CategoryRepository;
import com.productservice.services.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryResponseDto getcategory(UUID id) {
        Category category = categoryRepository.findById(id).orElseThrow(()-> new CategoryNotFoundException("Category not found related to this ID " + id));
        return CategoryMapper.toCategoryResponseDto(category);
    }

    @Transactional
    @Override
    public CategoryResponseDto addCategory(CategoryRequestDto categoryRequestDto) {
        if (categoryRepository.findByName(categoryRequestDto.name()).isPresent()){
            throw new CategoryAlreadyPresentException("Category of name " + categoryRequestDto.name()+  ",already exists");
        }
        Category category = CategoryMapper.toCategory(categoryRequestDto);
        Category savedCategory = categoryRepository.save(category);
        return CategoryMapper.toCategoryResponseDto(savedCategory);
    }
}
