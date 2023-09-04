package com.driver.service;

import com.driver.model.Booking;
import com.driver.model.Hotel;
import com.driver.model.User;
import com.driver.repository.BookingRepository;
import com.driver.repository.HotelRepository;
import com.driver.repository.UserRepository;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

@Service
public class BookingService {

    BookingRepository bookingRepository=new BookingRepository();


    HotelRepository hotelRepository=new HotelRepository();


    UserRepository userRepository=new UserRepository();
//    @Autowired
//    public BookingService(BookingRepository bookingRepository, HotelRepository hotelRepository, UserRepository userRepository) {
//        this.bookingRepository = bookingRepository;
//        this.hotelRepository = hotelRepository;
//        this.userRepository = userRepository;
//    }

    public int bookARoom(Booking booking) {
        if(booking==null)return -1;
        String hotelName=booking.getHotelName();
        if(hotelName==null)return -1;
        Hotel hotel=hotelRepository.getHotel(hotelName);
        if(hotel==null || booking.getNoOfRooms()>hotel.getAvailableRooms() )return -1;
        int roomsRemaining=hotel.getAvailableRooms()-booking.getNoOfRooms();
        hotel.setAvailableRooms(roomsRemaining);
        hotelRepository.addHotelToDB(hotel);
        int price=hotel.getPricePerNight()*booking.getNoOfRooms();
        booking.setAmountToBePaid(price);
        UUID bookingID= UUID.randomUUID();
        booking.setBookingId(String.valueOf(bookingID));
        bookingRepository.addBooking(booking);
        User user=userRepository.getUser(booking.getBookingAadharCard());
        int currBookingCount= user.getBookingCount();
        user.setBookingCount(currBookingCount+1);
        userRepository.addUser(user);
        return booking.getAmountToBePaid();
    }
}
