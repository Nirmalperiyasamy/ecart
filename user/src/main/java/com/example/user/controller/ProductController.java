package com.example.user.controller;

import com.example.user.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.example.user.utils.Constants.*;

@RequestMapping(API_PRODUCT)
@RestController
@Slf4j
public class ProductController {

    @Value("${addProduct}")
    protected String url;

    @Value("${byUser}")
    protected String url1;

    WebClient webClient = WebClient.create();

    @PostMapping(ADD)
    ResponseEntity<?> addProduct(@RequestBody ProductDto dto) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            User user = (User) principal;
            String username = user.getUsername();
            dto.setUuid(username);
        }


        Mono<ProductDto> dtoMono = Mono.just(dto);
        log.info(String.valueOf(dtoMono));

        return ResponseEntity.ok(webClient.post()
                .uri(url)
                .body(dtoMono, ProductDto.class)
                .retrieve().bodyToMono(ProductDto.class).block());
    }

    @GetMapping(PRODUCT_BY_USER)
    public ResponseEntity<?> byUser() {
        Mono<List<ProductDto>> dataMono = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToFlux(ProductDto.class)
                .collectList();
        return ResponseEntity.ok(dataMono);
    }

}
