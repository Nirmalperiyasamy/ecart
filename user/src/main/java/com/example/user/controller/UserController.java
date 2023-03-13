package com.example.user.controller;

import com.example.user.Messages;
import com.example.user.dto.UserDto;
import com.example.user.globalexception.CustomException;
import com.example.user.interceptor.JwtUtil;
import com.example.user.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import static com.example.user.utils.Constants.*;

@RestController
@Slf4j
@RequestMapping(API)
public class UserController {

    @Autowired
    protected UserServiceImpl userService;

    @Autowired
    protected JwtUtil jwtUtil;

    @Autowired
    protected AuthenticationManager authenticationManager;

    @GetMapping(PING)
    public ResponseEntity<?> ping() {
        return ResponseEntity.ok("PONG");
    }

    @PostMapping(REGISTER)
    public ResponseEntity<?> register(@RequestBody UserDto dto) {
        userService.register(dto);
        return ResponseEntity.ok("Registered successfully");
    }


    @PostMapping(TOKEN)
    public ResponseEntity<?> generateToken(@RequestBody UserDto dto) throws Exception {

        UserDto anotherDto = userService.findByName(dto);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(anotherDto.getUid(), dto.getPassword()));
        } catch (Exception e) {
            throw new CustomException(Messages.INVALID);
        }
        return ResponseEntity.ok(jwtUtil.generateToken(anotherDto.getUid()));
    }


}
