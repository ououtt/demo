package com.example.demo.repository.impl;

import com.example.demo.DemoApplicationTests;
import com.example.demo.repository.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class UserRepositoryImplTest extends DemoApplicationTests {

    @Autowired
    UserRepository userRepository;

    @Test
    public void createUser() {

    }

    @Test
    public void findUserDOByUsername() {
    }

    @Test
    public void countUserByUsername() {
        int count = userRepository.countUserByUsername("aaa");
        System.out.println(count);
    }
}