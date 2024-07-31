package com.example.demo.service;

import com.example.demo.model.Reservation;

import java.util.Optional;

public interface ReservationService {
    Optional<Reservation> getReservationById(Long id);
    void saveReservation(Reservation reservation);
    void cancelReservation(Reservation reservation);

}
