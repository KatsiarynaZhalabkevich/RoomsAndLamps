package com.github.zhalabkevich.roomservice.repository;

import com.github.zhalabkevich.roomservice.model.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Sql(value = {"/create-country-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-room-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class RoomRepositoryTest {
    private final RoomRepository repository;

    @Autowired
    public RoomRepositoryTest(RoomRepository repository) {
        this.repository = repository;
    }

    @Test
    public void updateLampStateModifyQueryTest() {
        Room room = repository.findById(1L).orElse(null);
        Assertions.assertNotNull(room);
        room.setLampOn(true);
        int count = repository.updateRoomByLampOn(1L, true);
        Assertions.assertTrue(count==1);
    }
    @Test
    public void updateLampStateViaSaveTest(){
        Room room = repository.findById(1L).orElse(null);
        Assertions.assertNotNull(room);
        room.setLampOn(true);
        Room roomFromDB = repository.save(room);
        Assertions.assertTrue(roomFromDB.isLampOn());
    }
}
