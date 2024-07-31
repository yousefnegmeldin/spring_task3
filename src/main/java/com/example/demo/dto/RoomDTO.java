package com.example.demo.dto;

import com.example.demo.model.enums.RoomStatus;

import com.example.demo.model.enums.RoomType;

public record RoomDTO(Long id, RoomType type, RoomStatus status) {
}
