package com.ucbcba.demo.services;

import com.ucbcba.demo.Entities.Category;

public interface CategoryService {
    Iterable<Category> listAllCategories();

    void saveRestaurant(Category category);

    Category getRestaurant(Integer id);

    void deleteRestaurant(Integer id);
}
