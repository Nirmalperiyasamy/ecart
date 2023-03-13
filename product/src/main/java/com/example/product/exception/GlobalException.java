package com.example.product.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<?> exception(CustomException errors){
        return ResponseEntity.ok().body(errors);
    }
}
