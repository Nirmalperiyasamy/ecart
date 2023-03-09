package com.example.product.service;

import com.example.product.dao.ProductDao;
import com.example.product.dto.ProductDto;
import com.example.product.repository.ProductRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    protected ProductRepo productRepo;

    public ProductDao addProduct(ProductDto dto) {
        ProductDao dao = new ProductDao();
        BeanUtils.copyProperties(dto, dao);
        productRepo.save(dao);
        return dao;
    }
}
