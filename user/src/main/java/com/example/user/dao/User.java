package com.example.user.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "uuid", nullable = false, unique = true)
    private String uuid;

    private String username;

    private String password;
}
