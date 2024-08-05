package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.model.Room;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.mapper.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@Tag(name = "User API", description = "API for user management")
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

    @Operation(summary = "Get user by email", description = "Get user by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "user found by email", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid user type", content = @Content)
    })
    @GetMapping("/{email}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String email){
        User user = userService.getUserByEmail(email);
        return ResponseEntity.ok().body(UserMapper.toUserDTO(user));
    }
}
