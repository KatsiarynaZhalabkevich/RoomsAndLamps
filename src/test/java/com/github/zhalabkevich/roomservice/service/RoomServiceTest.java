package com.github.zhalabkevich.roomservice.service;

import com.github.zhalabkevich.roomservice.model.Country;
import com.github.zhalabkevich.roomservice.model.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Sql(value = {"/create-country-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-room-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class RoomServiceTest {
    private final RoomService service;

    @Autowired
    public RoomServiceTest(RoomService service) {
        this.service = service;
    }

    @Test
    @Rollback
    void addRoomTest() {
        Room room = new Room("Room 5", new Country(1L, "Belarus"));
        Room roomFromBD = service.addRoom(room);
        Assertions.assertEquals(room, roomFromBD);
    }

    @Test
    void getRoomsTest() {
        List<Room> rooms = service.getRooms();
        Assertions.assertNotEquals(0, rooms.size());
    }

    @Test
    @Rollback
    void getRoomByIdTest() {
        Room room = new Room("Room 5", new Country(1L, "Belarus"));
        Room savedRoom = service.addRoom(room);
        Room roomFromDB = service.getRoomById(5L);
        Assertions.assertEquals(savedRoom, roomFromDB);
    }

    @Test
    @Rollback
    void updateRoomTest() {
        Room room = new Room("Room 5", new Country(1L, "Belarus"));
        Room savedRoom = service.addRoom(room);
        savedRoom.setLampOn(true);
        Room roomFromDB = service.updateRoom(savedRoom);
        Assertions.assertTrue(roomFromDB.isLampOn());

    }


}
