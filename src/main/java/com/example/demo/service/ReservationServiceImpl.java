package com.example.demo.service;

import com.example.demo.dto.ReservationDTO;
import com.example.demo.mapper.ReservationMapper;
import com.example.demo.model.Reservation;
import com.example.demo.model.Room;
import com.example.demo.model.User;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.RoomRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ReservationServiceImpl implements ReservationService {

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
                .orElseThrow(() -> new RuntimeException("User not found"));
        Room room = roomRepository.findById(reservationDTO.roomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));
        Reservation reservation = ReservationMapper.toReservation(reservationDTO);
        reservation.setUser(user);
        reservation.setRoom(room);
        return reservationRepository.save(reservation);
    }


    @Override
    public void cancelReservation(Reservation reservation) {
        reservationRepository.delete(reservation);
    }
}
