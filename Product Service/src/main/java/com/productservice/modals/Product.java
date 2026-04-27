package com.productservice.modals;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity (name = "product")
public class Product extends BaseModel{
    private String name;
    @Column(name = "description", length = 500)
    private String description;
    private double price;
    private int quantity;
    private String imgPath;
    @ManyToOne
    private Category category;
}
