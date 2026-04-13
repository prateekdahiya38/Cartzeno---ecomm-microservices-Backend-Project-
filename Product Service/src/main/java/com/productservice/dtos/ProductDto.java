package com.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private String title;
    private String description;
    private double price;
    private int quantity;
    private String imgPath;
    private String category;
}
