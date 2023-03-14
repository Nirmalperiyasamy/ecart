package com.example.user.globalexception;

import com.example.user.Messages;

public class CustomException extends RuntimeException {

    public CustomException(Messages message) {
        super(String.valueOf(message));
    }
}
