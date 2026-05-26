package com.example.Service;

import com.example.Bean.User;

public interface UserService {

    String register(User user);

    User login(String email, String password);
}