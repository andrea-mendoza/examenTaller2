package com.ucbcba.demo.services;

import com.ucbcba.demo.Entities.LikeRestaurant;
import com.ucbcba.demo.repository.LikeRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class LikeRestaurantServiceImpl implements LikeRestaurantService{

    private LikeRestaurantRepository likeRestaurantRepository;

    @Autowired
    @Qualifier(value = "likeRestaurantRepository")
    public void setRestaurantRepository(LikeRestaurantRepository likeRestaurantRepository) {
        this.likeRestaurantRepository = likeRestaurantRepository;
    }

    @Override
    public Iterable<LikeRestaurant> listAllLikeRestaurant() {
        return likeRestaurantRepository.findAll();
    }

    @Override
    public void saveLikeRestaurant(LikeRestaurant likeRestaurant) {
        likeRestaurantRepository.save(likeRestaurant);
    }

    @Override
    public LikeRestaurant getLikeRestaurant(Integer id) {
        return likeRestaurantRepository.findOne(id);
    }

    @Override
    public void deleteLikeRestaurant(Integer id) {
        likeRestaurantRepository.delete(id);
    }
}
