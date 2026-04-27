package com.productservice.services;

import com.productservice.dtos.ProductRequestSelfDto;
import com.productservice.dtos.ProductResponseSelfDto;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductResponseSelfDto getSingleProduct(UUID id);
    List<ProductResponseSelfDto> getProductList();
    ProductResponseSelfDto addProduct(ProductRequestSelfDto productRequestSelfDto);
    ProductResponseSelfDto updateProduct(UUID id,ProductRequestSelfDto productRequestSelfDto);
    void deleteProduct(UUID id);
}
