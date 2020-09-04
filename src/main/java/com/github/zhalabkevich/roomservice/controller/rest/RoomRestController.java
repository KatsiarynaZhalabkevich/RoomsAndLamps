package com.github.zhalabkevich.roomservice.controller.rest;

import com.github.zhalabkevich.roomservice.model.Room;
import com.github.zhalabkevich.roomservice.service.CountryIPService;
import com.github.zhalabkevich.roomservice.service.RoomService;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomRestController {
    private final RoomService service;
    private final CountryIPService ipService;

    @Autowired
    public RoomRestController(RoomService service, CountryIPService ipService) {
        this.service = service;
        this.ipService = ipService;
    }

    @PostMapping("/room")
    public ResponseEntity<Room> createRoom(@RequestBody Room room) {
        Room newRoom = service.addRoom(room);
        return newRoom != null ? new ResponseEntity<>(newRoom, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@RequestBody Room room){
        Room updatedRoom = service.updateRoom(room);
        return updatedRoom!=null? new ResponseEntity<>(updatedRoom, HttpStatus.OK)
                :new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("")
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = service.getRooms();
        return rooms != null ? new ResponseEntity<>(rooms, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")//check client's country by IP
    public ResponseEntity<Room> getRoom(@PathVariable("id") Long id, HttpServletRequest request) {

        String clientIp = request.getRemoteAddr();
        String country = "";
        //192.168.1.105 can't find in DB
        try {
            country = ipService.getCountryByIP(clientIp);
        } catch (IOException | GeoIp2Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Room room = service.getRoomById(id);
        return room != null && room.getCountry().getName().equals(country) ? new ResponseEntity<>(room, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


}
