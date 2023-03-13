package com.example.product.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
public class OrderRecipt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String customer_uid;

    private String product_owner_uid;

    private String product;

    private Integer count;

    private Integer price;

    private String date;
}
