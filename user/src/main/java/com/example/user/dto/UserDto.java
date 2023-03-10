package com.example.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private int id;

    private String Uuid;

    private String username;

    private String password;


}
