package com.example.user.dto;

import lombok.Data;

@Data
public class OrderDto {


    private String customer_uid;

    private String product_owner_uid;

    private String product;

    private Integer count;

    private Integer price;

}
