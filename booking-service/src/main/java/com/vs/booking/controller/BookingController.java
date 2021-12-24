package com.vs.booking.controller;

import com.vs.booking.dto.BookingDTO;
import com.vs.booking.dto.BookingsDTO;
import com.vs.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.logging.Logger;


@RestController
@CrossOrigin
public class BookingController {
    final Logger logger = Logger.getLogger(BookingController.class.getName());

    @Autowired
    BookingService service;

    @GetMapping("/bookings")
    private BookingsDTO getAllBookings() {
        return service.getAllBookings();
    }

    @GetMapping("/bookings/customer/{customerId}")
    private BookingsDTO getBookingsByCustomer(@PathVariable("customerId") String customerId) {
        logger.info("Getting booking for customer - " + customerId);
        return service.getBookingsByCustomer(customerId);
    }

    @GetMapping("/bookings/{id}")
    private BookingDTO getBooking(@PathVariable("id") Integer id) {
        return service.getBookingById(id);
    }

    @PostMapping("/bookings")
    private BookingDTO createBooking(@RequestBody BookingDTO booking) {
        return service.saveBooking(booking);
    }

    @PutMapping("/bookings")
    private void updateBooking(@RequestBody BookingDTO booking) {
        service.updateBooking(booking);
    }

}
