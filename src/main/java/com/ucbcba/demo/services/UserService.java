package com.ucbcba.demo.services;

import com.ucbcba.demo.Entities.User;

public interface UserService {

    void save(User user);
    User findByUsername(String username);

    Iterable<User> listAllUsers();
}
