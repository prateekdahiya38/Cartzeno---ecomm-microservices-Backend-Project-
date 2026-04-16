package com.productservice.services.impl;

import com.productservice.clients.FakeStoreClient;
import com.productservice.dtos.ProductDto;
import com.productservice.exceptions.exceptions.FakeStoreException;
import com.productservice.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FakeStoreProductService implements ProductService {
    private final FakeStoreClient fakeStoreClient;

    public FakeStoreProductService(FakeStoreClient fakeStoreClient) {
        this.fakeStoreClient = fakeStoreClient;
    }


    @Override
    public ProductDto getSingleProduct(int id) throws FakeStoreException {
         return fakeStoreClient.getFakeStoreSingleProduct(id);
    }

    @Override
    public List<ProductDto> getAllProducts() throws FakeStoreException {
        return fakeStoreClient.getAllFakeStoreProducts();
    }

    @Override
    public ProductDto addProduct(ProductDto productDto) throws FakeStoreException {
        return fakeStoreClient.addFakeStoreProduct(productDto);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) throws FakeStoreException {
        return fakeStoreClient.updateFakeStoreProduct(productDto);
    }
}
