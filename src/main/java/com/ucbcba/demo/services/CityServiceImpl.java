package com.ucbcba.demo.services;

import com.ucbcba.demo.Entities.City;
import com.ucbcba.demo.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {

    private CityRepository cityRepository;
    @Autowired
    @Qualifier(value="cityRepository")
    public  void setCityRepository(CityRepository cityRepository){
        this.cityRepository = cityRepository;
    }
    @Override
    public Iterable<City> listAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public void saveCity(City city) {
        cityRepository.save(city);
    }

    @Override
    public City getCity(Integer id) {
        return cityRepository.findOne(id);
    }

    @Override
    public void deleteCity(Integer id) {
        cityRepository.delete(id);
    }
}
