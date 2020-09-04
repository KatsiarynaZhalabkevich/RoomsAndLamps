package com.github.zhalabkevich.roomservice.service;

import com.github.zhalabkevich.roomservice.model.Country;

import java.util.List;

public interface CountryService {
    Country addCountry(Country country);

    List<Country> addCountries(List<Country> countries);

    List<Country> getAll();
}
