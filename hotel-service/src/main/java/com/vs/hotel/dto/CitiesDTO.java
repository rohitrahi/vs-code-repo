package com.vs.hotel.dto;

import java.util.ArrayList;
import java.util.List;

public class CitiesDTO {
    List<CityDTO> cities = new ArrayList<>();

    public List<CityDTO> getCountries() {
        return cities;
    }

    public void addCity(CityDTO cityDTO) {
        cities.add(cityDTO);
    }
}
