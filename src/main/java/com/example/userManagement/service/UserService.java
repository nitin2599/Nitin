package com.example.userManagement.service;

import com.example.userManagement.Repository.UserRepository;
import com.example.userManagement.exption.UserNotFoundException;
import com.example.userManagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(UUID userId) {
        return userRepository.findById(userId).
                orElseThrow(() -> new UserNotFoundException(userId));
    }

    public User updateUser(UUID userId, User user) {
        user.setId(userId);
        return userRepository.save(user);
    }

    public void deleteUserById(UUID userId) {
        userRepository.deleteById(userId);
    }


}