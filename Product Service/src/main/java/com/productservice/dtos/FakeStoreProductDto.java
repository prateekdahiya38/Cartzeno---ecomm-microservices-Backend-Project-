package com.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private int id;
    private String title;
    private float price;
    private String description;
    private String category;
    private String image;
}
