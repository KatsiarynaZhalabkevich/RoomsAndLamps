package com.github.zhalabkevich.roomservice.service.impl;

import com.github.zhalabkevich.roomservice.model.Country;
import com.github.zhalabkevich.roomservice.repository.CountryRepository;
import com.github.zhalabkevich.roomservice.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository repo;

    @Autowired
    public CountryServiceImpl(CountryRepository repo) {
        this.repo = repo;
    }

    @Override
    public Country addCountry(Country country) {
        Country countryFromBD = repo.findCountryByName(country.getName());
        return countryFromBD == null ? repo.save(country) : null;
    }

    @Override //проверить на дублирование записей
    public List<Country> addCountries(List<Country> countries) {
        return repo.saveAll(countries);
    }

    @Override
    public List<Country> getAll() {

        return repo.findAll();
    }
}
