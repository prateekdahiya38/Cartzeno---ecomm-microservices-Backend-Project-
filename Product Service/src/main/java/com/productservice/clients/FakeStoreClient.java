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
}