package com.example.user.repository;

import com.example.user.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    User findByUid(String username);

    boolean existsByUsername(String username);
}
