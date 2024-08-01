package com.example.demo.dto;

import com.example.demo.model.enums.ReservationStatus;

import java.time.LocalDate;

public record ReservationDTO(Long roomId,
                             Long userId,
                             LocalDate checkInDate,
                             LocalDate checkOutDate,
                             ReservationStatus status) {
}
