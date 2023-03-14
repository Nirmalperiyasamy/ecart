package com.example.product.exception;

//import com.example.user.Messages;

import com.example.product.util.Messages;

public class CustomException extends RuntimeException {

    public CustomException(Messages errors) {
        super(String.valueOf(errors));
    }
}
