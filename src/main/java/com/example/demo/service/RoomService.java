package com.example.demo.service;

import com.example.demo.model.Room;

import java.util.ArrayList;
import java.util.Optional;

public interface RoomService {
    void saveRoom(Room room);
    Optional<Room> getRoomById(Long id);
    ArrayList<Room> getAllRooms();
    ArrayList<Room> getReadyRooms();

}
