package com.productservice.mappers;

import com.productservice.dtos.FakeStoreProductDto;
import com.productservice.dtos.ProductDto;

public class ProductMapper {

    public static ProductDto toProductDto(FakeStoreProductDto fakeStoreProductDto) {
        ProductDto productDto = new ProductDto();
        productDto.setId(fakeStoreProductDto.getId());
        productDto.setTitle(fakeStoreProductDto.getTitle());
        productDto.setDescription(fakeStoreProductDto.getDescription());
        productDto.setPrice(fakeStoreProductDto.getPrice());
        productDto.setCategory(fakeStoreProductDto.getCategory());
        productDto.setImgPath(fakeStoreProductDto.getImage());
        return productDto;
    }

    public static FakeStoreProductDto toFakeStoreProductDto(ProductDto productDto) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(productDto.getId());
        fakeStoreProductDto.setTitle(productDto.getTitle());
        fakeStoreProductDto.setDescription(productDto.getDescription());
        fakeStoreProductDto.setImage(productDto.getImgPath());
        fakeStoreProductDto.setCategory(productDto.getCategory());
        fakeStoreProductDto.setPrice((float) productDto.getPrice());
        return fakeStoreProductDto;
    }
}
