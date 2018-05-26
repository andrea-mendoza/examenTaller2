package com.ucbcba.demo.services;

import com.ucbcba.demo.Entities.Restaurant;

public interface RestaurantService {
    Iterable<Restaurant> listAllRestaurants();

    void saveRestaurant(Restaurant restaurant);

    Restaurant getRestaurant(Integer id);

    void deleteRestaurant(Integer id);

    //Iterable<Restaurant> getRestaurantLikename(String name);
}
