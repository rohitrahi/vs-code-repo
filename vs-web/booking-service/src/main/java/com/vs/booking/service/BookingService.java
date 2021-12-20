package com.vs.booking.service;

import com.vs.booking.dto.BookingDTO;
import com.vs.booking.dto.BookingsDTO;
import com.vs.booking.model.Booking;
import com.vs.booking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vs.booking.oci.OciHelperBean;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    BookingRepository repository;

    @Autowired
    OciHelperBean ociHelperBean;

    public BookingsDTO getAllBookings() {
        BookingsDTO bookingsDTO = new BookingsDTO();
        try {
            for (Booking booking : repository.findAll()) {
                bookingsDTO.addBooking(BookingMapper.getBookingDTO(booking));
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return bookingsDTO;
    }

    public BookingDTO getBookingById(Integer id) {
        BookingDTO bookingDTO = null;
        try {
            Optional<Booking> booking = repository.findById(id);
            if(booking.isPresent()) {
                bookingDTO = BookingMapper.getBookingDTO(booking.get());
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return bookingDTO;
    }



    public BookingDTO saveBooking(BookingDTO bookingDTO) {
        Booking booking = BookingMapper.getBooking(bookingDTO);
        try {
            booking = repository.save(booking);
	    ObjectMapper objectMapper = new ObjectMapper();
	    bookingDTO = BookingMapper.getBookingDTO(booking);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return bookingDTO;
    }

    public void updateBooking(BookingDTO bookingDTO) {
        try {
            repository.save(BookingMapper.getBooking(bookingDTO));
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void deleteBooking(Integer id) {
        try {
            repository.deleteById(id);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public BookingsDTO getBookingsByCustomer(String customerId) {
        BookingsDTO bookingsDTO = new BookingsDTO();
        try {
            for (Booking booking : repository.findBookingsByCustomerId(customerId)) {
                bookingsDTO.addBooking(BookingMapper.getBookingDTO(booking));
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return bookingsDTO;
    }

}
