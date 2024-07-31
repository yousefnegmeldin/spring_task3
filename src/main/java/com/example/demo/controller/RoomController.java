package com.example.demo.controller;

import com.example.demo.dto.RoomDTO;
import com.example.demo.model.Room;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/room")
public class RoomController {

    @GetMapping
    public ResponseEntity<ArrayList<Room>> getAvailableRooms(){

    }

    @GetMapping("/book/{id}")
    public ResponseEntity<Room> bookRoom(@PathVariable Long roomId){

    }

    @DeleteMapping("/book/{id}")
    public void cancelReservation(@PathVariable Long roomId){

    }

    @PatchMapping("/book/id")
    public ResponseEntity<Room> updateReservation(@PathVariable Long roomId)
}
