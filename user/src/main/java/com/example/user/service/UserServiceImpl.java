package com.example.user.service;

import com.example.user.Messages;
import com.example.user.dao.User;
import com.example.user.dto.UserDto;
import com.example.user.globalexception.CustomException;
import com.example.user.repository.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    protected UserRepo userRepository;

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {
        User user = userRepository.findByUid(uid);
        return new org.springframework.security.core.userdetails.User
                (user.getUid(), user.getPassword(), new ArrayList<>());
    }

    public void register(UserDto dto) {
        if (userExist(dto.getUsername())) {
            throw new CustomException(Messages.USERNAME_EXIST);
        } else {
            User dao = new User();
            dto.setUid(UUID.randomUUID().toString());
            BeanUtils.copyProperties(dto, dao);
            String password = new BCryptPasswordEncoder().encode(dto.getPassword());
            dao.setPassword(password);
            userRepository.save(dao);


        }

    }

    public UserDto findByName(UserDto dto) {
        if (userExist(dto.getUsername())) {
            User dao = userRepository.findByUsername(dto.getUsername());
            dto.setUid(dao.getUid());
            return dto;
        }
        throw new CustomException(Messages.NOT_REGISTERED);
    }

    boolean userExist(String username) {
        return userRepository.existsByUsername(username);
    }
}
