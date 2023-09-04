package com.driver.repository;

import com.driver.model.Hotel;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class HotelRepository {
    HashMap<String, Hotel> hotelHashMap;

    public HashMap<String, Hotel> getHotelHashMap() {
        return hotelHashMap;
    }

    public void setHotelHashMap(HashMap<String, Hotel> hotelHashMap) {
        this.hotelHashMap = hotelHashMap;
    }

    public String addHotelToDB(Hotel hotel) {

        hotelHashMap.put(hotel.getHotelName(),hotel);
        return "SUCCESS";
    }

    public Hotel getHotel(String hotelName) {
        return hotelHashMap.get(hotelName);
    }
}
