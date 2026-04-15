package com.productservice.clients;

import com.productservice.dtos.FakeStoreProductDto;
import com.productservice.dtos.ProductDto;
import com.productservice.exceptions.exceptions.FakeStoreException;
import com.productservice.mappers.ProductMapper;
import com.productservice.services.ProductService;
import jakarta.annotation.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FakeStoreClient {
    private final RestTemplate restTemplate;

    public FakeStoreClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ProductDto getFakeStoreSingleProduct(int id) throws FakeStoreException {
        String url = ProductService.FAKE_STORE_PRODUCT_URL.concat("/").concat(String.valueOf(id));
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDto = restTemplate.getForEntity(url, FakeStoreProductDto.class);
        if (fakeStoreProductDto.getBody() == null) {
            throw new FakeStoreException("Something went wrong...");
        }
        return ProductMapper.toProductDto(fakeStoreProductDto.getBody());
    }


    public List<ProductDto> getAllFakeStoreProducts() throws FakeStoreException {
        ResponseEntity<FakeStoreProductDto[]> fakeStoreProductArrDto = restTemplate.getForEntity(ProductService.FAKE_STORE_PRODUCT_URL, FakeStoreProductDto[].class);
        if (fakeStoreProductArrDto.getBody() == null) {
            throw new FakeStoreException("Something Went Wrong..");
        }
        FakeStoreProductDto[] response = fakeStoreProductArrDto.getBody();
        return Arrays.stream(response).map(ProductMapper::toProductDto).collect(Collectors.toList());
    }

    public ProductDto addFakeStoreProduct(ProductDto productDto) throws FakeStoreException {
        ResponseEntity<FakeStoreProductDto> fakeStoreProduct = restTemplate.postForEntity(ProductService.FAKE_STORE_PRODUCT_URL,productDto,FakeStoreProductDto.class);
        if (fakeStoreProduct.getBody() == null) {
            throw new FakeStoreException("Product is not added correctly in the fake store");
        }
        return ProductMapper.toProductDto(fakeStoreProduct.getBody());
    }
}