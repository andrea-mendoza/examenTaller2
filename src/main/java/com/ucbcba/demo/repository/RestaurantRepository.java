package com.ucbcba.demo.repository;

import com.ucbcba.demo.Entities.City;
import com.ucbcba.demo.Entities.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

@Transactional
public interface RestaurantRepository extends CrudRepository<Restaurant,Integer> {

    @Query("SELECT r FROM Restaurant r WHERE r.name LIKE '%name%'")
    Iterable<Restaurant> getRestaurantLikeName(@Param("name") String name);

    @Query("SELECT r from Restaurant r where r.city = :cityId")
    Iterable<Restaurant> getByCity(@Param("cityId") City cityId);
}
