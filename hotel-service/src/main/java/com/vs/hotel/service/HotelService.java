package com.vs.hotel.service;

import com.vs.hotel.dto.*;
import com.vs.hotel.model.City;
import com.vs.hotel.model.Country;
import com.vs.hotel.model.Hotel;
import com.vs.hotel.model.RoomType;
import com.vs.hotel.repository.CityRepository;
import com.vs.hotel.repository.CountryRepository;
import com.vs.hotel.repository.HotelRepository;
import com.vs.hotel.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class HotelService {

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    RoomTypeRepository roomTypeRepository;

    public HotelsDTO getAllHotels() {
        HotelsDTO hotelsDTO = new HotelsDTO();
        try {
            for (Hotel hotel : hotelRepository.findAll()) {
                hotelsDTO.addHotel(HotelMapper.getHotelDTO(hotel));
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return hotelsDTO;
    }

    public HotelDTO getHotelById(Integer id) {
        HotelDTO hotelDTO = null;
        try {
            Optional<Hotel> hotel = hotelRepository.findById(id);
            if(hotel.isPresent()) {
                hotelDTO = HotelMapper.getHotelDTO(hotel.get());
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return hotelDTO;
    }

    public void saveHotel(HotelDTO hotelDTO) {
        try {
            hotelRepository.save(HotelMapper.getHotel(hotelDTO));
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void updateHotel(HotelDTO hotelDTO) {
        try {
            Hotel hotel = HotelMapper.getHotel(hotelDTO);
            hotelRepository.save(hotel);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void deleteHotel(Integer id) {
        try {
            hotelRepository.deleteById(id);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public CountriesDTO getAllCountries() {
        CountriesDTO countriesDTO = new CountriesDTO();
        try {
            for (Country country : countryRepository.findAll()) {
                CountryDTO countryDTO = new CountryDTO();
                countryDTO.setCountryId(country.getCountryId());
                countryDTO.setCountryName(country.getCountryName());
                countriesDTO.addCountry(countryDTO);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return countriesDTO;
    }

    public CitiesDTO getAllCities() {
        CitiesDTO citiesDTO = new CitiesDTO();
        try {
            for (City city : cityRepository.findAll()) {
                CityDTO cityDTO = new CityDTO();
                cityDTO.setCityId(city.getCityId());
                cityDTO.setCountryId(city.getCountry().getCountryId());
                cityDTO.setCityName(city.getCityName());
                citiesDTO.addCity(cityDTO);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return citiesDTO;
    }

    public RoomTypesDTO getAllRoomTypes() {
        RoomTypesDTO roomTypesDTO = new RoomTypesDTO();
        try {
            for (RoomType roomType : roomTypeRepository.findAll()) {
                RoomTypeDTO roomTypeDTO = new RoomTypeDTO();
                roomTypeDTO.setRoomTypeId(roomType.getRoomTypeId());
                roomTypeDTO.setRoomTypeName(roomType.getRoomTypeName());
                roomTypeDTO.setPricePerNight(roomType.getPricePerNight());
                roomTypeDTO.setDescription(roomType.getDescription());
                roomTypesDTO.addRoomType(roomTypeDTO);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return roomTypesDTO;
    }


    public HotelsDTO searchHotels(Integer countryId, Integer cityId, Date checkInDate, Date checkOutDate, Integer numRooms) {
        HotelsDTO hotelsDTO = new HotelsDTO();
        return hotelsDTO;
    }
}
