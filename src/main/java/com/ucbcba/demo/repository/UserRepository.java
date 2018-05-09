package com.ucbcba.demo.repository;

import com.ucbcba.demo.Entities.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

}