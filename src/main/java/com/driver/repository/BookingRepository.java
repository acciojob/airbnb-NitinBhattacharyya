package com.driver.repository;

import com.driver.model.Booking;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.UUID;

@Repository
public class BookingRepository {
    HashMap<String , Booking> bookingHashMap;

    public HashMap<String, Booking> getBookingHashMap() {
        return bookingHashMap;
    }

    public void setBookingHashMap(HashMap<String , Booking> bookingHashMap) {
        this.bookingHashMap = bookingHashMap;
    }

    public void addBooking(Booking booking) {
        bookingHashMap.put(booking.getBookingId(),booking);
    }
}
