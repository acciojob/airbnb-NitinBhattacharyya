package com.driver.repository;

import com.driver.model.Booking;
import com.driver.model.Hotel;
import com.driver.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class HotelManagementRepo {
    HashMap<String, Booking> bookingHashMap;
    HashMap<String, Hotel> hotelHashMap;
    HashMap<Integer, User> userHashMap;


    public HotelManagementRepo() {
        bookingHashMap=new HashMap<>();
        hotelHashMap=new HashMap<>();
        userHashMap=new HashMap<>();
    }


    public void addBooking(Booking booking) {
        bookingHashMap.put(booking.getBookingId(),booking);
    }

    public String addHotelToDB(Hotel hotel) {

        hotelHashMap.put(hotel.getHotelName(),hotel);
        return "SUCCESS";
    }

    public Hotel getHotel(String hotelName) {
        return hotelHashMap.get(hotelName);
    }

    public Integer addUser(User user) {
        userHashMap.put(user.getaadharCardNo(),user);
        return user.getaadharCardNo();
    }

    public User getUser(int bookingAadharCard) {
        return userHashMap.get(bookingAadharCard);
    }

    public HashMap<String, Booking> getBookingHashMap() {
        return bookingHashMap;
    }

    public void setBookingHashMap(HashMap<String, Booking> bookingHashMap) {
        this.bookingHashMap = bookingHashMap;
    }

    public HashMap<String, Hotel> getHotelHashMap() {
        return hotelHashMap;
    }

    public void setHotelHashMap(HashMap<String, Hotel> hotelHashMap) {
        this.hotelHashMap = hotelHashMap;
    }

    public HashMap<Integer, User> getUserHashMap() {
        return userHashMap;
    }

    public void setUserHashMap(HashMap<Integer, User> userHashMap) {
        this.userHashMap = userHashMap;
    }
}
