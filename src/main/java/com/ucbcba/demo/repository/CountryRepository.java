package com.ucbcba.demo.repository;

import com.ucbcba.demo.Entities.Country;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface CountryRepository extends CrudRepository<Country, Integer> {

}
