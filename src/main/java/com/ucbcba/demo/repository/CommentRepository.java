package com.ucbcba.demo.repository;

import com.ucbcba.demo.Entities.Comment;
import com.ucbcba.demo.Entities.Restaurant;
import com.ucbcba.demo.Entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

@Transactional
public interface CommentRepository extends CrudRepository<Comment, Integer> {
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END from Comment c where c.user = :userId AND c.restaurant = :restaurantId")
    boolean existsComment(@Param("userId") User userId,
                          @Param("restaurantId") Restaurant restaurantId);
}