package com.example.product.service;

import com.example.product.dao.Product;
import com.example.product.dto.ProductDto;
import com.example.product.repository.ProductRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        List<Product> pro = productRepo.findByUuid(token);
        List<ProductDto> dto = convertToDtoList(pro);
        return dto;
    }

    private List<ProductDto> convertToDtoList(List<Product> pro) {
        List<ProductDto> dto = new ArrayList<>();
        for (Product product : pro) {
            dto.add(convertToDto(product));
        }
        return dto;
    }

    private ProductDto convertToDto(Product pro) {
        ProductDto dto = new ProductDto();
        dto.setUuid(pro.getUuid());
        dto.setProduct(pro.getProduct());
        dto.setPrice(pro.getPrice());
        return dto;
    }
}
