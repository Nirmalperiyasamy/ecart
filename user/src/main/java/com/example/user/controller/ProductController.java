package com.example.user.controller;

import com.example.user.Messages;
import com.example.user.dto.OrderDto;
import com.example.user.dto.ProductDto;
import com.example.user.globalexception.CustomException;
import com.example.user.interceptor.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;

import static com.example.user.utils.Constants.*;

@RequestMapping(API_PRODUCT)
@RestController
@Slf4j
public class ProductController {

    @Value("${add-product}")
    protected String url;


    @Autowired
    protected JwtUtil jwtUtil;

    WebClient webClient = WebClient.create();

    @PostMapping(ADD)
    ResponseEntity<?> addProduct(@RequestBody ProductDto dto) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            User user = (User) principal;
            String uid = user.getUsername();
            dto.setUid(uid);
        } else {
            throw new CustomException(Messages.INVALID);
        }

        Mono<ProductDto> dtoMono = Mono.just(dto);

        return ResponseEntity.ok(webClient.post()
                .uri(url + ADD_PRODUCT_URL)
                .body(dtoMono, ProductDto.class)
                .retrieve().bodyToMono(ProductDto.class).block());
    }

    WebClient webClient1 = WebClient.create();

    @GetMapping(PRODUCT_BY_USER)
    public ResponseEntity<?> byUser(HttpServletRequest request) {

        String header = request.getHeader("Authorization");
        String token = header.substring(7);
        String uid = jwtUtil.extractUsername(token);
        try {
            ProductDto[] productDto = webClient1.get()
                    .uri(uriBuilder -> uriBuilder
                            .scheme("http")
                            .host("localhost")
                            .port(5000)
                            .path(API_PRODUCT + PRODUCT_BY_USER)
                            .queryParam("uid", uid)
                            .build())
                    .retrieve()
                    .bodyToMono(ProductDto[].class).block();

            return ResponseEntity.ok(productDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    WebClient webClient2 = WebClient.create();

    @PostMapping(ORDER)
    ResponseEntity<?> order(@RequestBody OrderDto orderDto) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            User user = (User) principal;
            String uid = user.getUsername();
            orderDto.setCustomerUid(uid);
        } else {
            throw new CustomException(Messages.INVALID);
        }

        Mono<OrderDto> orderMono = Mono.just(orderDto);

        return ResponseEntity.ok(webClient2.post()
                .uri(url + PLACE_ORDER_URL)
                .body(orderMono, OrderDto.class)
                .retrieve().bodyToMono(OrderDto.class).subscribe());
    }

}
