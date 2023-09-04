package com.driver.service;

import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class HotelService {

    HotelRepository hotelRepository=new HotelRepository();
//    @Autowired
//    public HotelService(HotelRepository hotelRepository) {
//        this.hotelRepository = hotelRepository;
//    }

    public String addHotelToDB(Hotel hotel) {
        HashMap<String,Hotel> map=hotelRepository.getHotelHashMap();
        if(hotel==null || hotel.getHotelName()==null || map.containsKey(hotel.getHotelName()))
        {
            return "FAILURE";
        }
        return hotelRepository.addHotelToDB(hotel);
    }

    public String getHotelWithMostFacilities() {
        HashMap<String,Hotel> map=hotelRepository.getHotelHashMap();
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
        Hotel hotel=hotelRepository.getHotel(hotelName);
        for(Facility facility:newFacilities)
        {
            if(!hotel.getFacilities().contains(facility))
            {
                hotel.getFacilities().add(facility);
            }
        }
        hotelRepository.addHotelToDB(hotel);
        return hotel;
    }
}
