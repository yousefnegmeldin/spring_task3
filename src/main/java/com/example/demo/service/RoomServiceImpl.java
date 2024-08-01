package com.example.demo.service;

import com.example.demo.model.Room;
import com.example.demo.model.enums.RoomStatus;
import com.example.demo.model.enums.RoomType;
import com.example.demo.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class RoomServiceImpl implements RoomService{

    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }


    @Override
    public void saveRoom(Room room) {
        roomRepository.save(room);
    }

    @Override
    public Optional<Room> getRoomById(Long id) {
        return roomRepository.findById(id);
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public List<Room> getRoomByType(RoomType roomType) {
        return this.getAllRooms().stream().filter(r -> r.getType() == roomType).toList();
    }

    @Override
    public List<Room> getReadyRooms() {
        try {
            return  this.getAllRooms().stream()
                    .filter(r -> r.getStatus() == RoomStatus.READY )
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Error filtering ready rooms: " + e.getMessage());
            return new ArrayList<>();
        }
    }


}
