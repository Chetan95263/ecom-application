package com.app.ecom_application;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final List<User> userList = new ArrayList<>();
    private Long currentId = 1L;
    public Optional<User> fetchUser(Long id){
        return userList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }
    public void addUser(User user) {
        user.setId(currentId++);
        userList.add(user);
    }
    public List<User> fetchAllUser(){
        return userList;
    }
    public boolean updateUser(Long id , User updatedUser) {
        return userList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .map(existedUser -> {
                    existedUser.setFirstName(updatedUser.getFirstName());
                    existedUser.setLastName(updatedUser.getLastName());
                    return true;
                }).orElse(false);
    }

}
