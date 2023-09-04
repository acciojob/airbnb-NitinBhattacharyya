package com.driver.service;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import com.driver.repository.HotelManagementRepo;
//import com.driver.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class HotelService {

    HotelManagementRepo hotelManagementRepo=new HotelManagementRepo();


    public String addHotelToDB(Hotel hotel) {
        HashMap<String,Hotel> map=hotelManagementRepo.getHotelHashMap();
        if(hotel==null || hotel.getHotelName()==null || map.containsKey(hotel.getHotelName()))
        {
            return "FAILURE";
        }
        return hotelManagementRepo.addHotelToDB(hotel);
    }

    public String getHotelWithMostFacilities() {
        HashMap<String,Hotel> map=hotelManagementRepo.getHotelHashMap();
        String hotelName="";
        int maxCount=0;
        for(Hotel hotel: map.values())
        {
            if(hotel.getFacilities().size()>maxCount)
            {
                maxCount=hotel.getFacilities().size();
                hotelName=hotel.getHotelName();
            }
            if(hotel.getFacilities().size()==maxCount)
            {
                if(hotelName.compareTo(hotel.getHotelName())>0)
                {
                    hotelName=hotel.getHotelName();
                }
            }
        }
        return hotelName;
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {
        Hotel hotel=hotelManagementRepo.getHotel(hotelName);
        for(Facility facility:newFacilities)
        {
            if(!hotel.getFacilities().contains(facility))
            {
                hotel.getFacilities().add(facility);
            }
        }
        hotelManagementRepo.addHotelToDB(hotel);
        return hotel;
    }

    public int bookARoom(Booking booking) {
        Hotel hotel=hotelManagementRepo.getHotel(booking.getHotelName());
        if(hotel==null || booking.getNoOfRooms()>hotel.getAvailableRooms())return -1;
        int roomsRemaining=hotel.getAvailableRooms()-booking.getNoOfRooms();
        hotel.setAvailableRooms(roomsRemaining);
        hotelManagementRepo.addHotelToDB(hotel);
        int price=hotel.getPricePerNight()*booking.getNoOfRooms();
        booking.setAmountToBePaid(price);
        UUID bookingID= UUID.randomUUID();
        booking.setBookingId(String.valueOf(bookingID));
        hotelManagementRepo.addBooking(booking);
//        User user=hotelManagementRepo.getUser(booking.getBookingAadharCard());
//        int currBookingCount=0;
//        if(user!=null){
//            currBookingCount=user.getBookingCount();
//            user.setBookingCount(currBookingCount+1);
//            hotelManagementRepo.addUser(user);
//        }


        return booking.getAmountToBePaid();
    }

    public Integer addUser(User user) {
        return hotelManagementRepo.addUser(user);
    }

    public int getBookingCount(Integer aadharCard) {
//        User user=hotelManagementRepo.getUser(aadharCard.intValue());
//        if(user==null)return 0;
//        return user.getBookingCount();
        return hotelManagementRepo.getBookingCount(aadharCard);

    }
}
