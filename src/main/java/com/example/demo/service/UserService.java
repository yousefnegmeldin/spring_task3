package com.example.demo.service;

import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.model.User;

import java.util.Optional;

public interface UserService {
    void saveUser(User user);
    Optional<User> getUserById(Long id);
    User getUserByEmail(String email) throws UserNotFoundException;
}
