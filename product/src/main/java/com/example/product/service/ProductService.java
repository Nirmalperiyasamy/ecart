package com.example.product.service;

import com.example.product.dao.Product;
import com.example.product.dto.ProductDto;
import com.example.product.repository.ProductRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    protected ProductRepo productRepo;

    public ProductDto addProduct(ProductDto dto) {
        Product dao = new Product();
        BeanUtils.copyProperties(dto, dao);
        productRepo.save(dao);
        return dto;
    }

    public List<ProductDto> productByUser(String token) {
        List<Product> products = productRepo.findByUid(token);
        List<ProductDto> dto = convertToDtoList(products);
        return dto;
    }

    private List<ProductDto> convertToDtoList(List<Product> products) {
        List<ProductDto> dto = products.stream().map(product -> this.convertToDto(product)).collect(Collectors.toList());
        return dto;
    }

    private ProductDto convertToDto(Product pro) {
        ProductDto dto = new ProductDto();
        dto.setUid(pro.getUid());
        dto.setProduct(pro.getProduct());
        dto.setPrice(pro.getPrice());
        return dto;
    }
}
