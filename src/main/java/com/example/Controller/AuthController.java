package com.example.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.Bean.Patient;
import com.example.Service.AuthService;
import com.example.dto.ApiResponse;
import com.example.dto.ForgotPasswordRequest;
import com.example.dto.RegisterRequest;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/register")
    public Patient register(@RequestBody RegisterRequest request) {
        return service.registerPatient(request);
    }
    @PutMapping("/forgot-password")
    public ApiResponse forgotPassword(
            @RequestBody
            ForgotPasswordRequest request) {

        service.forgotPassword(request);

        return new ApiResponse(
                true,
                "Password Updated Successfully",
                null
        );
    }
}