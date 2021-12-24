package com.vs.hotel.repository;

import com.vs.hotel.model.Country;
import com.vs.hotel.model.Hotel;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, Integer> {
}
