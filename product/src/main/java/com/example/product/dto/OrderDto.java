package com.example.product.dto;

import lombok.Data;

import javax.persistence.criteria.CriteriaBuilder;

@Data
public class OrderDto {

    private String customerUid;

    private String productOwnerUid;

    private String product;

    private Integer count;

    private String date;

    private Integer price;

}
