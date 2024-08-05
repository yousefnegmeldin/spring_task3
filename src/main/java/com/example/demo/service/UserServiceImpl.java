package com.example.demo.service;

import com.example.demo.exceptions.ReservationAlreadyExistsException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.mapper.ReservationMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Reservation;
import com.example.demo.model.Room;
import com.example.demo.model.User;
import com.example.demo.model.enums.ReservationStatus;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImpl implements UserService{


    private final UserRepository userRepository;;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        Optional<User> user = userRepository.getUserByEmail(email);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        return user.get();
    }
}
