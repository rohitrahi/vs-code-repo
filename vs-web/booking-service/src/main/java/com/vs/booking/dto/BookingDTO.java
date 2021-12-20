package com.vs.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
    Integer bookingId;
    String customerId;
    float bookingAmount;
    Date bookingDate;
    Integer hotelId;
    String hotelName;
    Integer roomTypeId;
    String roomTypeName;
    Date checkInDate;
    Date checkOutDate;
    Integer numberOfNights;
    boolean paymentCompleted;
}
