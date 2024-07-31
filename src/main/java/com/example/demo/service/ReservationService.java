package com.example.demo.service;

import com.example.demo.dto.ReservationDTO;
import com.example.demo.model.Reservation;

import java.util.Optional;

public interface ReservationService {
    Optional<Reservation> getReservationById(Long id);
    Reservation saveReservation(ReservationDTO reservationDTO);
    void cancelReservation(Reservation reservation);

}
