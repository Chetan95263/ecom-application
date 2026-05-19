package com.app.ecom_application;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("api/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        
        return ResponseEntity.ok(userService.fetchUser(id));
    }

    @PostMapping("api/users")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok("User added successfully");
    }

}
