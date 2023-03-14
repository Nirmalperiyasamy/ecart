package com.example.user.dto;

import lombok.Data;

@Data
public class OrderDto {


    private String customerUid;

    private String productOwnerUid;

    private String product;

    private Integer count;

    private Integer price;

}
