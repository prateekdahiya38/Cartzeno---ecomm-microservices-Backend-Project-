package com.productservice.services;

import com.productservice.dtos.FakeStoreProductDto;
import com.productservice.dtos.FakeStoreProductRequestDto;
import com.productservice.exceptions.exceptions.FakeStoreException;

import java.util.List;


public interface FakeStoreProductService {
    String FAKE_STORE_PRODUCT_URL = "https://fakestoreapi.com/products";

    FakeStoreProductDto getSingleProduct(int id) throws FakeStoreException;
    List<FakeStoreProductDto> getAllProducts() throws FakeStoreException;
    FakeStoreProductDto addProduct(FakeStoreProductRequestDto fakeStoreProductRequestDto) throws FakeStoreException;
    FakeStoreProductDto updateProduct(int id, FakeStoreProductRequestDto fakeStoreProductRequestDto) throws FakeStoreException;
    void deleteProduct(int id);
}
