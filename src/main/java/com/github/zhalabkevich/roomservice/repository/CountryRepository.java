package com.github.zhalabkevich.roomservice.repository;

import com.github.zhalabkevich.roomservice.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {
    Country findCountryByName(String countryName);
}
