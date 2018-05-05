package com.ucbcba.demo.repository;

import com.ucbcba.demo.Entities.Restaurant;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface RestaurantRepository extends CrudRepository<Restaurant,Integer> {
}
