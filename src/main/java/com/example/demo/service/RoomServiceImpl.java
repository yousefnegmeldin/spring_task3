package com.example.demo.service;

import com.example.demo.model.Room;
import com.example.demo.model.enums.RoomStatus;
import com.example.demo.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public ArrayList<Room> getAllRooms() {
        return (ArrayList<Room>) roomRepository.findAll();
    }

    @Override
    public ArrayList<Room> getReadyRooms() {
        return (ArrayList<Room>) this.getAllRooms()
                .stream()
                .filter(r -> r.getStatus() == RoomStatus.READY)
                .collect(Collectors.toList());
    }

}
