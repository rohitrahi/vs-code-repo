package com.vs.hotel.dto;

import java.util.ArrayList;
import java.util.List;

public class HotelsDTO {
    List<HotelDTO> hotels = new ArrayList<>();

    public List<HotelDTO> getHotels() {
        return hotels;
    }

    public void addHotel(HotelDTO hotelDTO) {
        hotels.add(hotelDTO);
    }
}
