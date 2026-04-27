package com.productservice.services.impl;

import com.productservice.dtos.CategoryResponseDto;
import com.productservice.dtos.ProductRequestSelfDto;
import com.productservice.dtos.ProductResponseSelfDto;
import com.productservice.exceptions.exceptions.CategoryNotFoundException;
import com.productservice.exceptions.exceptions.ProductNotFoundException;
import com.productservice.mappers.CategoryMapper;
import com.productservice.mappers.ProductMapper;
import com.productservice.modals.Category;
import com.productservice.modals.Product;
import com.productservice.repositories.CategoryRepository;
import com.productservice.repositories.ProductRepository;
import com.productservice.services.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductResponseSelfDto getSingleProduct(UUID id){
        Product product = productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("Product not found"));
        return ProductMapper.toProductResponseSelfDto(product);
    }

    @Override
    public List<ProductResponseSelfDto> getProductList() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toProductResponseSelfDto)
                .toList();
    }


    @Transactional
    @Override
    public ProductResponseSelfDto addProduct(ProductRequestSelfDto productRequestSelfDto) {
        Product product = ProductMapper.toProduct(productRequestSelfDto);
        Category category = categoryRepository.findById(productRequestSelfDto.category()).orElseThrow(()-> new CategoryNotFoundException("Category not found of ID " +  productRequestSelfDto.category()));
        product.setCategory(category);
        return ProductMapper.toProductResponseSelfDto(productRepository.save(product));
    }

    @Transactional
    @Override
    public ProductResponseSelfDto updateProduct(UUID id,ProductRequestSelfDto productRequestSelfDto) {
        Product product = productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("Product not found"));
        ProductMapper.updateProduct(product, productRequestSelfDto);
        if  (productRequestSelfDto.category()!=null && (product.getCategory()==null || !(productRequestSelfDto.category().equals(product.getCategory().getId())))) {
            product.setCategory(categoryRepository.findById(productRequestSelfDto.category()).orElseThrow(() -> new CategoryNotFoundException("Category not found of ID " + productRequestSelfDto.category())));
        }
        return ProductMapper.toProductResponseSelfDto(productRepository.save(product));
    }

    @Override
    public void deleteProduct(UUID id) {
        if (!productRepository.existsById(id)){
            throw new ProductNotFoundException("Product not found");
        }
        productRepository.deleteById(id);
    }
}
