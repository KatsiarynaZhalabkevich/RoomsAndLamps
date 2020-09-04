package com.github.zhalabkevich.roomservice.service.impl;

import com.github.zhalabkevich.roomservice.model.Room;
import com.github.zhalabkevich.roomservice.repository.RoomRepository;
import com.github.zhalabkevich.roomservice.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository repo;

    @Autowired
    public RoomServiceImpl(RoomRepository repo) {
        this.repo = repo;
    }

    @Override
    public Room addRoom(Room room) {
        Room roomFromBD = repo.findByName(room.getName());
        return roomFromBD == null ? repo.save(room) : null;
    }

    @Override
    public List<Room> getRooms() {
        return repo.findAll();
    }

    @Override
    public Room getRoomById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override//or use @Modify @Query instead of @DynamicUpdate
    //use repo.update
    public Room updateRoom(Room room) {
        Room roomFromDB = repo.findById(room.getId()).orElse(null);
        if (roomFromDB != null) {
            roomFromDB.setLampOn(room.isLampOn());
        }
        return repo.save(roomFromDB);
    }
}
