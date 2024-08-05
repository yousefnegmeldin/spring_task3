package com.example.demo.service;

import com.example.demo.dto.ReservationDTO;
import com.example.demo.exceptions.ReservationAlreadyExistsException;
import com.example.demo.mapper.ReservationMapper;
import com.example.demo.model.Reservation;
import com.example.demo.model.Room;
import com.example.demo.model.User;
import com.example.demo.model.enums.ReservationStatus;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.RoomRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ReservationServiceImpl implements ReservationService {

    String userNotFoundMsg = "User not found";
    String roomNotFoundMsg = "Room not found";
    String reservationExistsMsg = "Reservation already exists with same room id and user";
    String reservationNotFound = "Reservation not found";

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, UserRepository userRepository, RoomRepository roomRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
    }


    @Override
    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }


    public Reservation saveReservation(ReservationDTO reservationDTO) {
        User user = userRepository.findById(reservationDTO.userId())
                .orElseThrow(() -> new RuntimeException(userNotFoundMsg));
        Room room = roomRepository.findById(reservationDTO.roomId())
                .orElseThrow(() -> new RuntimeException(roomNotFoundMsg));
        Optional<Reservation> reservationCheck = reservationRepository
                .findByUserIdAndRoomId(reservationDTO.userId(), reservationDTO.roomId());
        if (reservationCheck.isPresent()) {
            throw new ReservationAlreadyExistsException(reservationExistsMsg);
        }

        Reservation reservation = ReservationMapper.toReservation(reservationDTO);
        if (reservationDTO.status() == null) {
            reservation.setStatus(ReservationStatus.PENDING);
        }
        reservation.setUser(user);
        reservation.setRoom(room);
        return reservationRepository.save(reservation);
    }


    public Reservation updateReservation(ReservationDTO reservationDTO) {
        User user = userRepository.findById(reservationDTO.userId())
                .orElseThrow(() -> new RuntimeException(userNotFoundMsg));
        Room room = roomRepository.findById(reservationDTO.roomId())
                .orElseThrow(() -> new RuntimeException(roomNotFoundMsg));
        Reservation reservation = reservationRepository.findByUserIdAndRoomId(reservationDTO.userId(), reservationDTO.roomId())
                .orElseThrow(() -> new RuntimeException(reservationNotFound));

        reservation.setStatus(reservationDTO.status() != null ? reservationDTO.status() : reservation.getStatus());
        reservation.setCheckInDate(reservationDTO.checkInDate());
        reservation.setCheckOutDate(reservationDTO.checkOutDate());

        return reservationRepository.save(reservation);
    }




    @Override
    public void cancelReservation(Reservation reservation) {
        reservationRepository.delete(reservation);
    }
}
