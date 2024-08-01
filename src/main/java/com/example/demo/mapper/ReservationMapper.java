package com.example.demo.mapper;

import com.example.demo.dto.ReservationDTO;
import com.example.demo.model.Reservation;
import com.example.demo.model.enums.ReservationStatus;

public class ReservationMapper {


    public static Reservation toReservation(ReservationDTO reservationDTO) {
        if (reservationDTO == null) return null;

        Reservation reservation = new Reservation();
        reservation.setCheckInDate(reservationDTO.checkInDate());
        reservation.setCheckOutDate(reservationDTO.checkOutDate());
        reservation.setStatus(reservationDTO.status());
        return reservation;
    }

    public static ReservationDTO toReservationDTO(Reservation reservation){
        if(reservation == null) return null;

        return new ReservationDTO(
                reservation.getRoom().getId(),
                reservation.getUser().getId(),
                reservation.getCheckInDate(),
                reservation.getCheckOutDate(),
                reservation.getStatus()
        );
    }
}
