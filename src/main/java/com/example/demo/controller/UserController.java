package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public void createUser(@RequestBody UserDTO user){
        userService.saveUser(UserMapper.toUser(user));
    }


    @GetMapping("/{email}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String email){
        Optional<User> user = userService.getUserByEmail(email);
        return user
                .map(value -> ResponseEntity.ok().body(UserMapper.toUserDTO(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
