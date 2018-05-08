package com.ucbcba.demo.services;

import com.ucbcba.demo.Entities.City;

public interface CityService {
    Iterable<City> listAllCities();

    void saveCity(City category);

    City getCity(Integer id);

    void deleteCity(Integer id);
}
