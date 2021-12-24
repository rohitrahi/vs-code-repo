package com.vs.hotel.controller;

import com.vs.hotel.dto.*;
import com.vs.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;
import java.util.Date;

@CrossOrigin
@RestController
public class HotelController {

    final Logger logger = Logger.getLogger(HotelController.class.getName());


    @Autowired
    HotelService service;

    @GetMapping("/search")
    private HotelsDTO searchHotels(@RequestParam Integer countryId, @RequestParam Integer cityId, @RequestParam Date cin, @RequestParam Date cout, @RequestParam Integer numRooms) {

        return service.searchHotels(countryId, cityId, cin, cout, numRooms);
    }

    @GetMapping("/hotels")
    private HotelsDTO getAllHotels() {
        logger.info("Getting all hotels...");
        return service.getAllHotels();
    }


    @GetMapping("/hotels/{id}")
    private HotelDTO getHotel(@PathVariable("id") Integer id) {
        return service.getHotelById(id);
    }

    @PostMapping("/hotels")
    private void createHotel(@RequestBody HotelDTO hotel) {
        service.saveHotel(hotel);
    }

    @PutMapping("/hotels")
    private void updateHotel(@RequestBody HotelDTO hotel) {
        service.updateHotel(hotel);
    }

    @DeleteMapping("/hotels/{id}")
    private void deleteHotel(@PathVariable("id") Integer id) {
        service.deleteHotel(id);
    }

    @GetMapping("/countries")
    private CountriesDTO getAllCountries() {
        return service.getAllCountries();
    }

    @GetMapping("/cities")
    private CitiesDTO getAllCities() {
        return service.getAllCities();
    }

    @GetMapping("/roomtypes")
    private RoomTypesDTO getAllRoomTypes() {
        return service.getAllRoomTypes();
    }

    @GetMapping("/greeting")
    private String greeting() {
        return "Welcome to Vision Stays!!!";
    }
}
