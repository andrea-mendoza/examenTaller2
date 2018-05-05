package com.ucbcba.demo.services;

import com.ucbcba.demo.Entities.Restaurant;
import com.ucbcba.demo.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class RestaurantServiceImp implements RestaurantService {

    private RestaurantRepository restaurantRepository;

    @Autowired
    @Qualifier(value = "postRepository")
    public void setPostRepository(RestaurantRepository restaurantRepository) {
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
        return restaurantRepository.findById(id).get();
    }

    @Override
    public void deleteRestaurant(Integer id) {
        restaurantRepository.deleteById(id);
    }
}
