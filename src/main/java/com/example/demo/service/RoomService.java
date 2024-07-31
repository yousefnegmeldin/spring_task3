package com.example.demo.service;

import com.example.demo.model.Room;
import java.util.List;
import java.util.Optional;

public interface RoomService {
    void saveRoom(Room room);
    Optional<Room> getRoomById(Long id);
    List<Room> getAllRooms();
    List<Room> getReadyRooms();

}
