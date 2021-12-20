package com.vs.booking.dto;

import java.util.ArrayList;
import java.util.List;

public class BookingsDTO {
    List<BookingDTO> bookings = new ArrayList<>();

    public List<BookingDTO> getBookings() {
        return bookings;
    }

    public void addBooking(BookingDTO bookingDTO) {
        bookings.add(bookingDTO);
    }
}
