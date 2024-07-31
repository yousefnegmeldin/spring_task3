package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO user){

    }


    @GetMapping
    public UserDTO getUser(@RequestBody String email){

    }
}
