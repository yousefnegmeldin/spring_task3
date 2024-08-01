package com.example.demo.controller;

import com.example.demo.dto.ReservationDTO;
import com.example.demo.dto.RoomDTO;
import com.example.demo.exceptions.ReservationAlreadyExistsException;
import com.example.demo.mapper.ReservationMapper;
import com.example.demo.mapper.RoomMapper;
import com.example.demo.model.Reservation;
import com.example.demo.model.Room;
import com.example.demo.model.enums.ReservationStatus;
import com.example.demo.model.enums.RoomType;
import com.example.demo.service.ReservationService;
import com.example.demo.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {

    private final RoomService roomService;
    private final ReservationService reservationService;

    @Autowired
    public RoomController(RoomService roomService, ReservationService reservationService) {
        this.roomService = roomService;
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity createRoom(@RequestBody RoomDTO room){
        roomService.saveRoom(RoomMapper.toRoom(room));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/available")
    public ResponseEntity<List<Room>> getAvailableRooms(){
        return ResponseEntity.ok().body(roomService.getReadyRooms());
    }

    @GetMapping("/{type}")
    public ResponseEntity<List<Room>> getRoomByFilter(@PathVariable RoomType type){
        return ResponseEntity.ok().body(roomService.getRoomByType(type));
    }


    @PostMapping("/book")
    public ResponseEntity<?> bookRoom(@RequestBody ReservationDTO reservationDTO) {
        try {
            Reservation reservationToBook = reservationService.saveReservation(reservationDTO);
            return ResponseEntity.ok().body(reservationToBook);
        } catch (ReservationAlreadyExistsException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{roomId}")
    public ResponseEntity cancelReservation(@PathVariable Long roomId){
        Optional<Reservation> reservation = reservationService.getReservationById(roomId);
        if(reservation.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        reservationService.cancelReservation(reservation.get());
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Reservation> updateReservation(@RequestBody ReservationDTO reservationDTO){
        Reservation reservationToBook = reservationService.updateReservation(reservationDTO);
        return ResponseEntity.ok().body(reservationToBook);
//        Reservation updatedReservation = reservationService.saveReservation(ReservationMapper.toReservation(reservationDTO));
//        return ResponseEntity.ok().body(updatedReservation);
    }
}
