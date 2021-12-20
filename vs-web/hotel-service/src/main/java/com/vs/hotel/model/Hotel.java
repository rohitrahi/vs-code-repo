package com.vs.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "HOTELS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Hotel implements Serializable {
    @Id
    @Column(name = "HOTEL_ID")
    Integer hotelId;
    @Column(name = "HOTEL_NAME")
    String hotelName;
    @Column(name = "ADDRESS")
    String address;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CITY_ID")
    City city;
    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY)
    Set<Room> rooms = new HashSet<>();
    public void addRoom(Room room) {
        rooms.add(room);
        room.setHotel(this);
    }
}