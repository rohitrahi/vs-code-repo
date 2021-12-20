package com.vs.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomTypeDTO {
    Integer roomTypeId;
    String roomTypeName;
    Float pricePerNight;
    String description;
}
