package com.example.user.controller;

import com.example.user.dto.UserDto;
import com.example.user.interseptor.JwtUtil;
import com.example.user.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import static com.example.user.utils.Constants.*;

@RestController
@RequestMapping(API)
public class UserController {

    @Autowired
    protected UserServiceImpl userService;

    @Autowired
    protected JwtUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;

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
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        } catch (Exception e) {
            throw new Exception("Invalid username or password");
        }
        return ResponseEntity.ok(jwtUtil.generateToken(dto.getUsername()));
    }


}
