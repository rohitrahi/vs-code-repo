package com.vs.hotel.dto;

import java.util.ArrayList;
import java.util.List;

public class RoomTypesDTO {
    List<RoomTypeDTO> roomTypes = new ArrayList<>();

    public List<RoomTypeDTO> getRoomTypes() {
        return roomTypes;
    }

    public void addRoomType(RoomTypeDTO roomTypeDTO) {
        roomTypes.add(roomTypeDTO);
    }
}
