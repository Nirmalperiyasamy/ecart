package com.example.product.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.criteria.CriteriaBuilder;

@Data
@Builder
public class ProductDto {
    private Integer id;

    private String uid;

    private String product;

    private Integer price;

    private Integer fixed_count;

    private Integer count;
}
