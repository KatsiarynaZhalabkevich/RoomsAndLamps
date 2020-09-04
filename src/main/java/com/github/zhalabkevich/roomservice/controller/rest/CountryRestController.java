package com.github.zhalabkevich.roomservice.controller.rest;

import com.github.zhalabkevich.roomservice.model.Country;
import com.github.zhalabkevich.roomservice.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryRestController {
    private final CountryService service;

    public CountryRestController(CountryService service) {
        this.service = service;
    }

    @PostMapping("/country")
    public ResponseEntity<Country> addCountry(@RequestBody Country country) {
        Country newCountry = service.addCountry(country);
        return newCountry != null ? new ResponseEntity<>(newCountry, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @PostMapping("/countries")
    public ResponseEntity<List<Country>> addSeveralCountries(@RequestBody List<Country> countries) {
        List<Country> newCountries = service.addCountries(countries);
        return newCountries != null ? new ResponseEntity<>(newCountries, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("")
    public ResponseEntity<List<Country>> getCountries() {
        List<Country> countries = service.getAll();
        return countries!=null? new ResponseEntity<>(countries, HttpStatus.OK)
                :new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
