package com.example.demo.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo
.entity.User;
import com.example.demo.exception.*;
import com.example.demo.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
@Service
public class UserService {

    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    // CREATE
    public User createUser(User user) {
        if (userRepo.existsByEmail(user.getEmail())) {
            throw new DuplicateResourceException("Email already exists");
        }
        return userRepo.save(user);
    }

    // READ BY ID
    public User getUserById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    // READ ALL
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    // UPDATE
    public User updateUser(Long id, User updatedUser) {

        User user = getUserById(id);

        // Basic fields
        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());

        // 🔥 Location fields (NEW)
        user.setCity(updatedUser.getCity());
        user.setState(updatedUser.getState());
        user.setCountry(updatedUser.getCountry());

        return userRepo.save(user);
    }

    // DELETE
    public void deleteUser(Long id) {
        User user = getUserById(id);
        userRepo.delete(user);
    }
   

    public User getCurrentUser() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

}
