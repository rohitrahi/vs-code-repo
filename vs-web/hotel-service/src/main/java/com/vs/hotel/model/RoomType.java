package com.vs.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "ROOM_TYPES")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomType implements Serializable {
    @Id
    @Column(name = "ROOM_TYPE_ID")
    Integer roomTypeId;
    @Column(name = "ROOM_TYPE_NAME")
    String roomTypeName;
    @Column(name = "PRICE_PER_NIGHT")
    Float pricePerNight;
    @Column(name = "DESCRIPTION")
    String description;
}