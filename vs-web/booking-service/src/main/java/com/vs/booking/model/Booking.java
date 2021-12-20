package com.vs.booking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "BOOKINGS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue
    @Column(name = "BOOKING_ID")
    Integer bookingId;
    @Column(name = "CUSTOMER_ID")
    String customerId;
    @Column(name = "BOOKING_AMOUNT")
    float bookingAmount;
    @Column(name = "BOOKING_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    Date bookingDate;
    @Column(name = "HOTEL_ID")
    Integer hotelId;
    @Column(name = "HOTEL_NAME")
    String hotelName;
    @Column(name = "ROOM_TYPE_ID")
    Integer roomTypeId;
    @Column(name = "ROOM_TYPE_NAME")
    String roomTypeName;
    @Column(name = "CHECK_IN_DATE")
    @Temporal(TemporalType.DATE)
    Date checkInDate;
    @Column(name = "CHECK_OUT_DATE")
    @Temporal(TemporalType.DATE)
    Date checkOutDate;
    @Column(name = "NUMBER_OF_NIGHTS")
    Integer numberOfNights;
    @Column(name = "PAYMENT_COMPLETED")
    boolean paymentCompleted;
}