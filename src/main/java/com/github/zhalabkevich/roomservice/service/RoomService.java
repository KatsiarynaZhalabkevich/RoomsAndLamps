package com.github.zhalabkevich.roomservice.service;

import com.github.zhalabkevich.roomservice.model.Room;

import java.util.List;

public interface RoomService {
    Room addRoom(Room room);
    List<Room> getRooms();
    Room getRoomById(Long id);
    Room updateRoom(Room room);

}
