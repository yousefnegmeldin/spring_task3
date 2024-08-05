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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/room")
@Tag(name = "Room API", description = "API for room management")
public class RoomController {

    private final RoomService roomService;
    private final ReservationService reservationService;

    @Autowired
    public RoomController(RoomService roomService, ReservationService reservationService) {
        this.roomService = roomService;
        this.reservationService = reservationService;
    }

    @Operation(summary = "Create a room", description = "Create a new room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Room created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid room data", content = @Content)
    })
    @PostMapping
    public ResponseEntity<?> createRoom(@RequestBody RoomDTO room) {
        roomService.saveRoom(RoomMapper.toRoom(room));
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get available rooms", description = "Get a list of available rooms")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of available rooms", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Room.class)))
    })
    @GetMapping("/available")
    public ResponseEntity<List<Room>> getAvailableRooms() {
        return ResponseEntity.ok().body(roomService.getReadyRooms());
    }

    @Operation(summary = "Get rooms by type", description = "Get a list of rooms by type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of rooms by type", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Room.class))),
            @ApiResponse(responseCode = "400", description = "Invalid room type", content = @Content)
    })
    @GetMapping("/{type}")
    public ResponseEntity<List<RoomDTO>> getRoomByFilter(@PathVariable RoomType type) {
        List<RoomDTO> roomsFound = roomService
                .getRoomByType(type)
                .stream()
                .map(RoomMapper::toRoomDTO)
                .toList();
        return ResponseEntity.ok().body(roomsFound);
    }

    @Operation(summary = "Reserve a room", description = "Book a room, the response contains the reservationDTO")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Room reserved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReservationDTO.class))),
            @ApiResponse(responseCode = "409", description = "Reservation already exists", content = @Content)
    })
    @PostMapping("/book")
    public ResponseEntity<?> bookRoom(@RequestBody ReservationDTO reservationDTO) {
        try {
            Reservation reservationToBook = reservationService.saveReservation(reservationDTO);
            return ResponseEntity.ok().body(ReservationMapper.toReservationDTO(reservationToBook));
        } catch (ReservationAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @Operation(summary = "Cancel a reservation", description = "Cancel a reservation by room ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation cancelled successfully"),
            @ApiResponse(responseCode = "404", description = "Reservation not found", content = @Content)
    })
    @DeleteMapping("/{roomId}")
    public ResponseEntity<?> cancelReservation(@PathVariable Long roomId) {
        Optional<Reservation> reservation = reservationService.getReservationById(roomId);
        if (reservation.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        reservationService.cancelReservation(reservation.get());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Update a reservation", description = "Update a reservation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReservationDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid reservation data", content = @Content)
    })
    @PutMapping
    public ResponseEntity<ReservationDTO> updateReservation(@RequestBody ReservationDTO reservationDTO) {
        Reservation reservationToBook = reservationService.updateReservation(reservationDTO);
        return ResponseEntity.ok().body(ReservationMapper.toReservationDTO(reservationToBook));
    }
}
