package com.driver.service;

import com.driver.model.User;
import com.driver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Integer addUser(User user) {
        return userRepository.addUser(user);
    }

    public int getBookingCount(Integer aadharCard) {
        User user=userRepository.getUser(aadharCard);
        return user.getBookingCount();
    }
}
