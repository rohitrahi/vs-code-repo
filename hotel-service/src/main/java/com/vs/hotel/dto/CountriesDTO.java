package com.vs.hotel.dto;

import java.util.ArrayList;
import java.util.List;

public class CountriesDTO {
    List<CountryDTO> countries = new ArrayList<>();

    public List<CountryDTO> getCountries() {
        return countries;
    }

    public void addCountry(CountryDTO countryDTO) {
        countries.add(countryDTO);
    }
}
