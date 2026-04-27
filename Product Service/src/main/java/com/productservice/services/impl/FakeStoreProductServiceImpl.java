package com.productservice.services.impl;

import com.productservice.clients.FakeStoreClient;
import com.productservice.dtos.FakeStoreProductDto;
import com.productservice.dtos.FakeStoreProductRequestDto;
import com.productservice.exceptions.exceptions.FakeStoreException;
import com.productservice.services.FakeStoreProductService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FakeStoreProductServiceImpl implements FakeStoreProductService {
    private final FakeStoreClient fakeStoreClient;

    public FakeStoreProductServiceImpl(FakeStoreClient fakeStoreClient) {
        this.fakeStoreClient = fakeStoreClient;
    }


    @Override
    public FakeStoreProductDto getSingleProduct(int id) throws FakeStoreException {
         return fakeStoreClient.getFakeStoreSingleProduct(id);
    }

    @Override
    public List<FakeStoreProductDto> getAllProducts() throws FakeStoreException {
        return fakeStoreClient.getAllFakeStoreProducts();
    }

    @Override
    public FakeStoreProductDto addProduct(FakeStoreProductRequestDto fakeStoreProductRequestDto) throws FakeStoreException {
        return fakeStoreClient.addFakeStoreProduct(fakeStoreProductRequestDto);
    }

    @Override
    public FakeStoreProductDto updateProduct(int id, FakeStoreProductRequestDto fakeStoreProductRequestDto) throws FakeStoreException {
        return fakeStoreClient.updateFakeStoreProduct(id, fakeStoreProductRequestDto);
    }

    @Override
    public void deleteProduct(int id) {
        fakeStoreClient.deleteFakeStoreProduct(id);
    }
}
