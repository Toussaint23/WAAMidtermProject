package com.example.security.demo.service;

import com.example.security.demo.model.User;

public interface UserService {
    public User findUserByEmail(String email);
    public void saveUser(User user);
}
