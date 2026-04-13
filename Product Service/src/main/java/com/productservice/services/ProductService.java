package com.productservice.services;

import com.productservice.dtos.ProductDto;
import com.productservice.exceptions.exceptions.FakeStoreException;


public interface ProductService {
    String FAKE_STORE_PRODUCT_URL = "https://fakestoreapi.com/products";

    ProductDto getSingleProduct (int id) throws FakeStoreException;
}
