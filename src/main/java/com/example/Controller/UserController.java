package com.example.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.Bean.User;
import com.example.Service.UserService;
import com.example.dto.ApiResponse;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService service;

    // Register
    @PostMapping("/register")
    public ApiResponse register(@RequestBody User user) {

        String result = service.register(user);

        if (result.equals("User Registered Successfully")) {
            return new ApiResponse(true, result, null);
        }

        return new ApiResponse(false, result, null);
    }

    // Login
    @PostMapping("/login")
    public ApiResponse login(@RequestBody User user) {

        User loggedIn = service.login(user.getEmail(), user.getPassword());

        if (loggedIn != null) {
            loggedIn.setPassword(null); // hide password
            return new ApiResponse(true, "Login Success", loggedIn);
        }

        return new ApiResponse(false, "Invalid Credentials", null);
    }
}