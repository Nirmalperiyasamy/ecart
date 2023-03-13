package com.example.product.dto;

import lombok.Data;

import javax.persistence.criteria.CriteriaBuilder;

@Data
public class OrderDto {

    private String customer_uid;

    private String product_owner_uid;

    private String product;

    private Integer count;

    private String date;

    private Integer price;

}
