package com.ucbcba.demo.repository;

import com.ucbcba.demo.Entities.Category;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface CategoryRepository   extends CrudRepository<Category, Integer> {
}
