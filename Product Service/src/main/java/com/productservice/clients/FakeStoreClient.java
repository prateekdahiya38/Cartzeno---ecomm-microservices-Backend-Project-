package com.productservice.clients;

import com.productservice.dtos.FakeStoreProductDto;
import com.productservice.dtos.ProductDto;
import com.productservice.exceptions.exceptions.FakeStoreException;
import com.productservice.mappers.ProductMapper;
import com.productservice.services.ProductService;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDto = restTemplate.exchange(url, HttpMethod.GET, entity, FakeStoreProductDto.class);
        if (fakeStoreProductDto.getBody() == null) {
            throw new FakeStoreException("Something went wrong...");
        }
        return ProductMapper.toProductDto(fakeStoreProductDto.getBody());
    }



    public List<ProductDto> getAllFakeStoreProducts() throws FakeStoreException {
        ResponseEntity<FakeStoreProductDto[]> fakeStoreProductArrDto = restTemplate.exchange(ProductService.FAKE_STORE_PRODUCT_URL,HttpMethod.GET, null, FakeStoreProductDto[].class);
        if (fakeStoreProductArrDto.getBody() == null) {
            throw new FakeStoreException("Something Went Wrong..");
        }
        FakeStoreProductDto[] response = fakeStoreProductArrDto.getBody();
        return Arrays.stream(response).map(ProductMapper::toProductDto).collect(Collectors.toList());
    }



    public ProductDto addFakeStoreProduct(ProductDto productDto) throws FakeStoreException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ProductDto> entity = new HttpEntity<>(productDto, headers);
        ResponseEntity<FakeStoreProductDto> fakeStoreProduct = restTemplate.exchange(ProductService.FAKE_STORE_PRODUCT_URL,HttpMethod.POST,entity,FakeStoreProductDto.class);
        if (fakeStoreProduct.getBody() == null) {
            throw new FakeStoreException("Product is not added correctly in the fake store");
        }
        return ProductMapper.toProductDto(fakeStoreProduct.getBody());
    }


    public ProductDto updateFakeStoreProduct(ProductDto productDto) throws FakeStoreException {
        String url = ProductService.FAKE_STORE_PRODUCT_URL.concat("/").concat(String.valueOf(productDto.getId()));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ProductDto> entity = new HttpEntity<>(productDto, headers);
        ResponseEntity<FakeStoreProductDto> fakeStoreProduct = restTemplate.exchange(url,HttpMethod.PUT,entity,FakeStoreProductDto.class);
        if (fakeStoreProduct.getBody() == null) {
            throw new FakeStoreException("Product is not updated correctly in the fake store");
        }
        return ProductMapper.toProductDto(fakeStoreProduct.getBody());
    }
}