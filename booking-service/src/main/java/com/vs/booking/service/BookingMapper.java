package com.vs.booking.service;

import com.vs.booking.dto.BookingDTO;
import com.vs.booking.model.Booking;
import org.springframework.beans.BeanUtils;

public class BookingMapper {
    public static BookingDTO getBookingDTO(Booking booking) {
        BookingDTO bookingDTO = new BookingDTO();
        BeanUtils.copyProperties(booking, bookingDTO);
        return bookingDTO;
    }

    public static Booking getBooking(BookingDTO bookingDTO) {
        Booking booking = new Booking();
        BeanUtils.copyProperties(bookingDTO, booking);
        return booking;
    }
}
