package com.example.demo.model.enums;

public enum RoomStatus {
    READY("READY"),
    CLEANING("CLEANING"),
    BOOKED("BOOKED"),
    UNAVAILABLE("UNAVAILABLE"),
    MAINTENANCE("MAINTENANCE");

    private String name;

    RoomStatus(String name) {
        this.name = name;
    }
}
