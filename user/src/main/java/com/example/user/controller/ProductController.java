package com.example.user.controller;

import com.example.user.dto.ProductDto;
import com.example.user.interceptor.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriBuilderFactory;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
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

    @Autowired
    protected JwtUtil jwtUtil;

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

        return ResponseEntity.ok(webClient.post()
                .uri(url)
                .body(dtoMono, ProductDto.class)
                .retrieve().bodyToMono(ProductDto.class).block());
    }

    WebClient webClient1 = WebClient.create();

    @GetMapping(PRODUCT_BY_USER)
    public ResponseEntity<?> byUser(HttpServletRequest request) {

        String header = request.getHeader("Authorization");
        String token = header.substring(7);
        String uuid = jwtUtil.extractUsername(token);
        try {
            ProductDto[] productDto = webClient1.get()
                    .uri(uriBuilder -> uriBuilder
                            .scheme("http")
                            .host("localhost")
                            .port(5000)
                            .path("api/byUser")
                            .queryParam("uid", uuid)
                            .build())
                    .retrieve()
                    .bodyToMono(ProductDto[].class)
                    .block();

            return ResponseEntity.ok(productDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }

}
