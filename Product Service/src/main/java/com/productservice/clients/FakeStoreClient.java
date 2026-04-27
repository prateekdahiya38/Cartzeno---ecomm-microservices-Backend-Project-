package com.productservice.clients;

import com.productservice.dtos.FakeStoreProductDto;
import com.productservice.dtos.FakeStoreProductRequestDto;
import com.productservice.exceptions.exceptions.FakeStoreException;
import com.productservice.services.FakeStoreProductService;
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

    public FakeStoreProductDto getFakeStoreSingleProduct(int id) throws FakeStoreException {
        String url = FakeStoreProductService.FAKE_STORE_PRODUCT_URL.concat("/").concat(String.valueOf(id));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDto = restTemplate.exchange(url, HttpMethod.GET, entity, FakeStoreProductDto.class);
        if (fakeStoreProductDto.getBody() == null) {
            throw new FakeStoreException("Something went wrong...");
        }
        return fakeStoreProductDto.getBody();
    }



    public List<FakeStoreProductDto> getAllFakeStoreProducts() throws FakeStoreException {
        ResponseEntity<FakeStoreProductDto[]> fakeStoreProductArrDto = restTemplate.exchange(FakeStoreProductService.FAKE_STORE_PRODUCT_URL,HttpMethod.GET, null, FakeStoreProductDto[].class);
        if (fakeStoreProductArrDto.getBody() == null) {
            throw new FakeStoreException("Something Went Wrong..");
        }
        FakeStoreProductDto[] response = fakeStoreProductArrDto.getBody();
        return Arrays.stream(response).collect(Collectors.toList());
    }



    public FakeStoreProductDto addFakeStoreProduct(FakeStoreProductRequestDto fakeStoreProductRequestDto) throws FakeStoreException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<FakeStoreProductRequestDto> entity = new HttpEntity<>(fakeStoreProductRequestDto, headers);
        ResponseEntity<FakeStoreProductDto> fakeStoreProduct = restTemplate.exchange(FakeStoreProductService.FAKE_STORE_PRODUCT_URL,HttpMethod.POST,entity,FakeStoreProductDto.class);
        if (fakeStoreProduct.getBody() == null) {
            throw new FakeStoreException("Product is not added correctly in the fake store");
        }
        return fakeStoreProduct.getBody();
    }


    public FakeStoreProductDto updateFakeStoreProduct(int id, FakeStoreProductRequestDto fakeStoreProductRequestDto) throws FakeStoreException {
        String url = FakeStoreProductService.FAKE_STORE_PRODUCT_URL.concat("/").concat(String.valueOf(id));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<FakeStoreProductRequestDto> entity = new HttpEntity<>(fakeStoreProductRequestDto, headers);
        ResponseEntity<FakeStoreProductDto> fakeStoreProduct = restTemplate.exchange(url,HttpMethod.PUT,entity,FakeStoreProductDto.class);
        if (fakeStoreProduct.getBody() == null) {
            throw new FakeStoreException("Product is not updated correctly in the fake store");
        }
        return fakeStoreProduct.getBody();
    }


    public void deleteFakeStoreProduct(int id){
        String url = FakeStoreProductService.FAKE_STORE_PRODUCT_URL.concat("/").concat(String.valueOf(id));
        restTemplate.exchange(url,HttpMethod.DELETE,null,String.class);
    }
}