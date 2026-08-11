package com.example.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Bean.User;
import com.example.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repo;

    @Override
    public String register(User user) {

        if (repo.findByEmail(user.getEmail()).isPresent()) {
            return "Email already exists";
        }

        // ⚠️ Plain password (ok for now, not production)
        repo.save(user);

        return "User Registered Successfully";
    }

    @Override
    public User login(String email, String password) {

        User user = repo.findByEmail(email).orElse(null);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }
}