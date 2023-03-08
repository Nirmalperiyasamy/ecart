package com.example.user.repository;

import com.example.user.dao.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserDao, Integer> {

    UserDao findByUsername(String username);
}
