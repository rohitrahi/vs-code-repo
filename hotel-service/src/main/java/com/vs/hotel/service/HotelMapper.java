package com.vs.hotel.service;

import com.vs.hotel.dto.HotelDTO;
import com.vs.hotel.model.Hotel;
import org.springframework.beans.BeanUtils;

public class HotelMapper {
    public static HotelDTO getHotelDTO(Hotel hotel) {
        HotelDTO hotelDTO = new HotelDTO();
        BeanUtils.copyProperties(hotel, hotelDTO);
        hotelDTO.setCountryId(hotel.getCity().getCountry().getCountryId());
        hotelDTO.setCountryName(hotel.getCity().getCountry().getCountryName());
        hotelDTO.setCityId(hotel.getCity().getCityId());
        hotelDTO.setCityName(hotel.getCity().getCityName());
        return hotelDTO;
    }

    public static Hotel getHotel(HotelDTO hotelDTO) {
        Hotel hotel = new Hotel();
        BeanUtils.copyProperties(hotelDTO, hotel);
        hotel.getCity().getCountry().setCountryId(hotelDTO.getCountryId());
        hotel.getCity().getCountry().setCountryName(hotelDTO.getCountryName());
        hotel.getCity().setCityId(hotelDTO.getCityId());
        hotel.getCity().setCityName(hotelDTO.getCityName());
        return hotel;
    }
}
