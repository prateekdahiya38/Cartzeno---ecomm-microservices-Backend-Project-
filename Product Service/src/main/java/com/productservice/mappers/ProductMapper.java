package com.productservice.mappers;

import com.productservice.dtos.FakeStoreProductDto;
import com.productservice.dtos.ProductDto;
import com.productservice.modals.Product;

public class ProductMapper {

    public static ProductDto toProductDto(FakeStoreProductDto fakeStoreProductDto) {
        ProductDto productDto = new ProductDto();
        productDto.setTitle(fakeStoreProductDto.getTitle());
        productDto.setDescription(fakeStoreProductDto.getDescription());
        productDto.setPrice(fakeStoreProductDto.getPrice());
        productDto.setCategory(fakeStoreProductDto.getCategory());
        productDto.setImgPath(fakeStoreProductDto.getImage());
        return productDto;
    }
}
