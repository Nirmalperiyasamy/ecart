package com.example.user.dto;

import lombok.Data;

@Data
public class ProductDto {

    private Integer id;

    private String uid;

    private String product;

    private Integer price;

    private Integer fixed_count;

    private Integer count;
}
