package com.example.userManagement.serivce;

import com.example.userManagement.Repository.UserRepository;
import com.example.userManagement.controller.UserController;
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
                orElseThrow(() -> new RuntimeException("User Not Found"));
    }

    public User updateUser(User user){
         return userRepository.save(user);
    }

    public void deleteUserById(UUID userId){
        userRepository.deleteById(userId);
    }
}