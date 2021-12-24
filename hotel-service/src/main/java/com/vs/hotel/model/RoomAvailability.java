package com.vs.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ROOM_AVAILABILITY")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomAvailability implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "AVAILABILITY_DATE")
    @Temporal(TemporalType.DATE)
    Date availabilityDate;
    @Id
    @ManyToOne
    @JoinColumn(name = "HOTEL_ID")
    Hotel hotel;
    @Id
    @ManyToOne
    @JoinColumn(name = "ROOM_TYPE_ID")
    RoomType roomType;
    @Column(name = "QTY_AVAILABLE")
    Integer qtyAvailable;
}