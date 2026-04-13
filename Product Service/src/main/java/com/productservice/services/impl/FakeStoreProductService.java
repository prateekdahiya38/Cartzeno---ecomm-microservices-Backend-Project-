package com.productservice.services.impl;

import com.productservice.clients.FakeStoreClient;
import com.productservice.dtos.ProductDto;
import com.productservice.exceptions.exceptions.FakeStoreException;
import com.productservice.services.ProductService;
import org.springframework.stereotype.Service;


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
}
