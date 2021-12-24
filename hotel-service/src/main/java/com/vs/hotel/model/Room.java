package com.vs.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ROOMS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    @Column(name = "ROOM_ID")
    Integer roomId;
    @ManyToOne
    @JoinColumn(name = "HOTEL_ID")
    Hotel hotel;
    @ManyToOne
    @JoinColumn(name = "ROOM_TYPE_ID")
    RoomType roomType;
}