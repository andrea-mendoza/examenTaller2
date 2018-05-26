package com.ucbcba.demo.repository;

import com.ucbcba.demo.Entities.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

@Transactional
public interface RestaurantRepository extends CrudRepository<Restaurant,Integer> {

    /*@Query("select p from Restaurant r where r.name like '%:name%'")
    Iterable<Restaurant> getRestaurantLikename(@Param("name") String name);*/
}
