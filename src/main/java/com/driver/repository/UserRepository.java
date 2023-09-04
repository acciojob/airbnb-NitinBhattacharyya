package com.driver.repository;

import com.driver.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class UserRepository {
    HashMap<Integer, User> userHashMap;

    public HashMap<Integer, User> getUserHashMap() {
        return userHashMap;
    }

    public void setUserHashMap(HashMap<Integer, User> userHashMap) {
        this.userHashMap = userHashMap;
    }

    public Integer addUser(User user) {
        userHashMap.put(user.getaadharCardNo(),user);
        return user.getaadharCardNo();
    }

    public User getUser(int bookingAadharCard) {
        return userHashMap.get(bookingAadharCard);
    }
}
