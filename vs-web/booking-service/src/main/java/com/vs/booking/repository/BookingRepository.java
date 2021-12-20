package com.vs.booking.repository;

import com.vs.booking.model.Booking;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookingRepository extends CrudRepository<Booking, Integer> {
    List<Booking> findBookingsByCustomerId(String customerId);
}
