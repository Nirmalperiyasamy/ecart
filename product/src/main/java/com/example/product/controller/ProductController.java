package com.example.product.controller;

import com.example.product.dao.Product;
import com.example.product.dto.ProductDto;
import com.example.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.product.util.Constants.ADD_PRODUCT;
import static com.example.product.util.Constants.API;

@RestController
@Slf4j
@RequestMapping(API)
public class ProductController {
    @Autowired
    protected ProductService productService;

    @PostMapping(ADD_PRODUCT)
    public Product addProduct(@RequestBody ProductDto dto) {

        return productService.addProduct(dto);
    }
}
