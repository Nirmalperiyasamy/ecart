package com.example.user.controller;

import com.example.user.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static com.example.user.utils.Constants.ADD;
import static com.example.user.utils.Constants.API_PRODUCT;

@RequestMapping(API_PRODUCT)
@RestController
@Slf4j
public class ProductController {

    @PostMapping(ADD)
    ResponseEntity<?> addProduct(@RequestBody ProductDto dto) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            User user = (User) principal;
            String username = user.getUsername();
            dto.setUsername(username);
        }

        WebClient webClient = WebClient.create();
        Mono<ProductDto> dtoMono = Mono.just(dto);
        log.info(String.valueOf(dtoMono));

        return ResponseEntity.ok(webClient.post()
                .uri("http://localhost:5000/addProduct")
                .body(dtoMono, ProductDto.class)
                .retrieve().bodyToMono(ProductDto.class).block());
    }
}
