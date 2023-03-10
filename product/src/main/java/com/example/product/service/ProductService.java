package com.example.product.service;

import com.example.product.dao.Product;
import com.example.product.dto.ProductDto;
import com.example.product.repository.ProductRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    protected ProductRepo productRepo;

    public Product addProduct(ProductDto dto) {
        Product dao = new Product();
        BeanUtils.copyProperties(dto, dao);
        productRepo.save(dao);
        return dao;
    }
}
