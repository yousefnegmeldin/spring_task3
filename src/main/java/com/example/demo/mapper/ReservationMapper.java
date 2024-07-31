package com.example.demo.mapper;

import com.example.demo.dto.ReservationDTO;
import com.example.demo.model.Reservation;
import com.example.demo.model.enums.ReservationStatus;

public class ReservationMapper {

    public static Reservation toReservation(ReservationDTO reservationDTO){
        if(reservationDTO == null)return null;

        return new Reservation(
                reservationDTO.userId(),
                reservationDTO.roomId(),
                reservationDTO.checkInDate(),
                reservationDTO.checkOutDate(),
                ReservationStatus.PENDING);
    }

    public static ReservationDTO toReservationDTO(Reservation reservation){
        if(reservation == null) return null;

        return new ReservationDTO(
                reservation.getRoomId(),
                reservation.getUserId(),
                reservation.getCheckInDate(),
                reservation.getCheckOutDate()
        );
    }
}
