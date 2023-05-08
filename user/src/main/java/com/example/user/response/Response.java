package com.example.user.response;

import com.example.user.dto.UserDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class Response<T> {
    private HttpStatus status;

    private String message;

    private T data;

    public Response(HttpStatus status, String message , T data){
        this.status = status;
        this.message = message;
        this.data = data;
    }

}
