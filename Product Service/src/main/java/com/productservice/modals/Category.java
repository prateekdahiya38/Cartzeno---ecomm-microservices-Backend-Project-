package com.productservice.modals;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity (name = "category")
public class Category extends BaseModel{
    private String name;
    @Column(name = "description",length = 500)
    private String description;
}
