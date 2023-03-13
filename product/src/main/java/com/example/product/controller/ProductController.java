package com.example.product.controller;

import com.example.product.dao.Product;
import com.example.product.dto.ProductDto;
import com.example.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.example.product.util.Constants.*;

@RestController
@Slf4j
@RequestMapping(API)
public class ProductController {
    @Autowired
    protected ProductService productService;

    @PostMapping(ADD_PRODUCT)
    public ProductDto addProduct(@RequestBody ProductDto dto) {
        return productService.addProduct(dto);
    }

    @GetMapping(PRODUCT_BY_USER)
    public List<ProductDto> productByUser(@RequestParam("uid") String uid) {
        return productService.productByUser(uid);
    }
}
