package com.app.ecom_application.service;

import com.app.ecom_application.model.User;
import com.app.ecom_application.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    public Optional<User> fetchUser(Long id){
        return userRepo.findById(id);
    }
    public void addUser(User user) {
        userRepo.save(user);
    }
    public List<User> fetchAllUser(){
        return userRepo.findAll();
    }
    public boolean updateUser(Long id , User updatedUser) {
        return userRepo.findById(id).map(existingUser -> {
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getFirstName());
            return true;
        }).orElse(false);
    }

}
