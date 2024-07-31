package com.example.demo.dto;

import java.util.Date;

public record ReservationDTO(Long roomId, Long userId, Date checkInDate, Date checkOutDate) {
}
