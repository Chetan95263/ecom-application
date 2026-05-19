package com.app.ecom_application;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private List<User> userList = new ArrayList<>();
    private Long currentId = 1L;
    public User fetchUser(Long id){
        return userList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    public void addUser(User user) {
        user.setId(currentId++);
        userList.add(user);
    }

}
