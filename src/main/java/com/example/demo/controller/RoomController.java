package com.example.demo.controller;

import com.example.demo.dto.BookRoomRequestDTO;
import com.example.demo.dto.PatchReservationDTO;
import com.example.demo.dto.RoomDTO;
import com.example.demo.model.Reservation;
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

    @PostMapping("/book")
    public ResponseEntity<Room> bookRoom(@RequestBody BookRoomRequestDTO request){
        Long roomId = request.roomId();
        Long userID = request.userId();

    }

    @DeleteMapping("/{id}")
    public void cancelReservation(@PathVariable Long roomId){

    }

    @PatchMapping("/{id}")
    //change it to dto later
    public ResponseEntity<Reservation> updateReservation(@RequestBody PatchReservationDTO request){

    }
}
