package com.example.demo.mapper;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;

public class UserMapper {
    public static User toUser(UserDTO userDTO){
        if(userDTO == null) return null;
        return new User(userDTO.name(),userDTO.email(), userDTO.phone());
    }

    public static UserDTO toUserDTO(User user){
        if(user == null) return null;
        return new UserDTO(user.getName(), user.getEmail(), user.getPhone());
    }
}
