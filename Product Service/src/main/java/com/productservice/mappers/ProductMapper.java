package com.productservice.mappers;

import com.productservice.dtos.ProductRequestSelfDto;
import com.productservice.dtos.ProductResponseSelfDto;
import com.productservice.modals.Product;

public class ProductMapper {

    public static ProductResponseSelfDto toProductResponseSelfDto(Product product){
        return new ProductResponseSelfDto(product.getId(),product.getName(), product.getDescription(), product.getPrice(), product.getQuantity(), product.getImgPath(),product.getCategory().getName());
    }

    public static Product toProduct(ProductRequestSelfDto productRequestSelfDto){
        Product product = new Product();
        product.setName(productRequestSelfDto.title());
        product.setDescription(productRequestSelfDto.description());
        product.setPrice(productRequestSelfDto.price());
        product.setImgPath(productRequestSelfDto.imagePath());
        product.setQuantity(productRequestSelfDto.quantity());
        return product;
    }

    public static Product updateProduct(Product product,ProductRequestSelfDto productRequestSelfDto){
        if(productRequestSelfDto.title()!=null) {
            product.setName(productRequestSelfDto.title());
        }
        if (productRequestSelfDto.description()!=null) {
            product.setDescription(productRequestSelfDto.description());
        }
        if (productRequestSelfDto.price() != product.getPrice()) {
            product.setPrice(productRequestSelfDto.price());
        }
        if (productRequestSelfDto.imagePath()!=null) {
            product.setImgPath(productRequestSelfDto.imagePath());
        }
        if (productRequestSelfDto.quantity()!=product.getQuantity()) {
            product.setQuantity(productRequestSelfDto.quantity());
        }
        return product;
    }
}
