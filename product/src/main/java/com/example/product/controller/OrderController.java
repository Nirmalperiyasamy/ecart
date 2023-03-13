package com.example.product.controller;

import com.example.product.dto.OrderDto;
import com.example.product.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.product.util.Constants.API_ORDER;
import static com.example.product.util.Constants.ORDER;

@RestController
@RequestMapping(API_ORDER)
public class OrderController {

    @Autowired
    protected OrderService orderService;

    @PostMapping(ORDER)
    public OrderDto order(@RequestBody OrderDto dto) {
        return orderService.orderProduct(dto);
    }
}
