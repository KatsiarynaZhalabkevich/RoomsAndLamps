package com.github.zhalabkevich.roomservice.controller;

import com.github.zhalabkevich.roomservice.model.Country;
import com.github.zhalabkevich.roomservice.model.Room;
import com.github.zhalabkevich.roomservice.service.CountryIPService;
import com.github.zhalabkevich.roomservice.service.CountryService;
import com.github.zhalabkevich.roomservice.service.RoomService;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/api")
public class RoomController {
    private final RoomService roomService;
    private final CountryService countryService;
    private final CountryIPService ipService;

    @Autowired
    public RoomController(RoomService roomService, CountryService countryService, CountryIPService ipService) {
        this.roomService = roomService;
        this.countryService = countryService;
        this.ipService = ipService;
    }

    @PostMapping("/room") //+
    public String createRoom(Room room, Model model) {
        Room roomFromDB = roomService.addRoom(room);
        if (roomFromDB != null) {
            return "redirect:/api/?message="+ "Room was created!";
        } else {
            return "redirect:/api/?message="+ "Room name isn't unique!";
        }
      //  return "redirect:/api/";
    }

    @GetMapping("/rooms")
    public String getRooms(Model model) {
        List<Room> rooms = roomService.getRooms();
        model.addAttribute("rooms", rooms);
        return "index";
    }

    @GetMapping("/") //+
    public String createForm(Model model, @RequestParam(value = "message",required = false) String message, HttpServletRequest request) {
        System.out.println("Context Path "+request.getContextPath());
        System.out.println("Path Info "+request.getPathInfo());
        List<Room> rooms = roomService.getRooms();
        model.addAttribute("rooms", rooms);
        List<Country> countries = countryService.getAll();
        model.addAttribute("countries", countries);
        model.addAttribute("newRoom", new Room());
        System.out.println(message);
        model.addAttribute("message",message);
        return "index";
    }

    @PostMapping("/room/{id}") //Put not supported
    public String updateRoomFields(@PathVariable Long id, boolean lampOn, Model model) {
        System.out.println("LAMP ON FROM PAGE"+lampOn);
        Room room = new Room();
        room.setLampOn(lampOn);
        room.setId(id);
        room = roomService.updateRoom(room);
        System.out.println("ROOM UPDATED"+room);
        model.addAttribute("room", room);
        return "room";
    }

    @GetMapping("/room/{id}") //+
    public String joinRoom(@PathVariable Long id, HttpServletRequest request, Model model) {
        String clientIp = request.getRemoteAddr();
        String country = "";
        //192.168.1.105 can't find in DB
        try {
            country = ipService.getCountryByIP(clientIp);
        } catch (IOException | GeoIp2Exception e) {
            return "redirect:/api/?message="+"Can't get info about your country";
        }
        Room room = roomService.getRoomById(id);
        if (room != null && room.getCountry().getName().equals(country)) {
            model.addAttribute("room", room);
            return "room";
        } else {
            return "redirect:/api/?message="+"Sorry! You can't join the room. Please, choose another one!";
        }

    }
}
