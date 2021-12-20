package com.vs.hotel.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelDTO {
    Integer hotelId;
    String hotelName;
    String address;
    @JsonProperty
    Integer cityId;
    @JsonProperty
    String cityName;
    @JsonProperty
    Integer countryId;
    @JsonProperty
    String countryName;
}
