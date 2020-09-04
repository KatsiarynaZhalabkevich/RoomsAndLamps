package com.github.zhalabkevich.roomservice.repository;

import com.github.zhalabkevich.roomservice.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long>, CrudRepository<Room, Long> {
    Room findByName(String roomName);
    @Transactional
    @Modifying
    @Query("UPDATE Room SET lampOn=:lampOn WHERE id=:id")
    int updateRoomByLampOn(@Param( "id") Long id, @Param("lampOn") boolean lampOn);

}
