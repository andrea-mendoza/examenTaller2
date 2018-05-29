package com.ucbcba.demo.services;

import com.ucbcba.demo.Entities.City;
import com.ucbcba.demo.Entities.Country;
import com.ucbcba.demo.Entities.Restaurant;
import com.ucbcba.demo.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RestaurantServiceImp implements RestaurantService {

    private RestaurantRepository restaurantRepository;

    @Autowired
    @Qualifier(value = "restaurantRepository")
    public void setRestaurantRepository(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Iterable<Restaurant> listAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public void saveRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant getRestaurant(Integer id) {
        return restaurantRepository.findOne(id);
    }

    @Override
    public void deleteRestaurant(Integer id) {
        restaurantRepository.delete(id);
    }

    @Override
    public Iterable<Restaurant> getRestaurantLikeName(String name) {
        return restaurantRepository.getRestaurantLikeName(name);
    }

    @Override
    public Iterable<Restaurant> getByCity(City cityId) {
        return restaurantRepository.getByCity(cityId);
    }

    @Override
    public Iterable<Restaurant> getByCityAndiD(City cityId, String name) {
        return restaurantRepository.getByCityAndiD(cityId,name);
    }

    @Override
    public Iterable<Restaurant> getByCountryAndCity(City cityId, Country countryId) {
        return restaurantRepository.getByCountryAndCity(cityId,countryId);
    }
}
