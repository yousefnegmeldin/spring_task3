package com.example.demo.mapper;

import com.example.demo.dto.RoomDTO;
import com.example.demo.model.Room;

public class RoomMapper {

    public static RoomDTO toRoomDTO(Room room){
        if(room == null) return null;
        return new RoomDTO(room.getId(),room.getType(),room.getStatus());
    }

    public static Room toRoom(RoomDTO roomDTO){
        if(roomDTO == null) return null;
        return new Room(roomDTO.type(),roomDTO.status());
    }
}
