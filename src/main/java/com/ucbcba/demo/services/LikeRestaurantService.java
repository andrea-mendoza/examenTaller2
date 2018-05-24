package com.ucbcba.demo.services;

import com.ucbcba.demo.Entities.LikeRestaurant;

public interface LikeRestaurantService {
    Iterable<LikeRestaurant> listAllLikeRestaurant();

    void saveLikeRestaurant(LikeRestaurant likeRestaurant);

    LikeRestaurant getLikeRestaurant(Integer id);

    void deleteLikeRestaurant(Integer id);
}
