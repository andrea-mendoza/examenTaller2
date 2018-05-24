package com.ucbcba.demo.repository;

import com.ucbcba.demo.Entities.LikeRestaurant;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface LikeRestaurantRepository extends CrudRepository<LikeRestaurant, Integer> {
}
