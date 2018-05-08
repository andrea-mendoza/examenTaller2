package com.ucbcba.demo.services;

import com.ucbcba.demo.Entities.Category;
import com.ucbcba.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    @Autowired
    @Qualifier(value="categoryRepository")
    public  void setPostCategoryRepository(CategoryRepository categoryRepository){
        this.categoryRepository=categoryRepository;
    }
    @Override
    public Iterable<Category> listAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void saveRestaurant(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Category getRestaurant(Integer id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public void deleteRestaurant(Integer id) {
        categoryRepository.deleteById(id);
    }
}
