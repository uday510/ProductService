package com.app.productservice.models;

import lombok.*;

@Getter
@Setter
public class Product {
    private Long id;
    private String title;
    private double price;
    private Category category;
    private String description;
    private String image;
}
