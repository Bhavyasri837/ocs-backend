package com.example.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import com.example.Bean.User;
import com.example.Repository.UserRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class OtpController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;

    private Map<String, String> otpStorage = new HashMap<>();

    @PostMapping("/send-otp")
    public Map<String, Object> sendOtp(@RequestBody Map<String, String> data) {
        Map<String, Object> response = new HashMap<>();

        String email = data.get("email");

        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            response.put("success", false);
            response.put("message", "Email not registered");
            return response;
        }

        String otp = String.valueOf(new Random().nextInt(900000) + 100000);
        otpStorage.put(email, otp);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("MEDICARE CONNECT Password Reset OTP");
        message.setText("Your OTP for password reset is: " + otp);

        try {
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();

            response.put("success", false);
            response.put("message", e.getMessage());
            return response;
        }

        response.put("success", true);
        response.put("message", "OTP sent successfully");
        return response;
    }

    @PostMapping("/verify-otp")
    public Map<String, Object> verifyOtp(@RequestBody Map<String, String> data) {
        Map<String, Object> response = new HashMap<>();

        String email = data.get("email");
        String otp = data.get("otp");

        String storedOtp = otpStorage.get(email);

        if (storedOtp == null) {
            response.put("success", false);
            response.put("message", "OTP expired or not found");
            return response;
        }

        if (!storedOtp.equals(otp)) {
            response.put("success", false);
            response.put("message", "Invalid OTP");
            return response;
        }

        response.put("success", true);
        response.put("message", "OTP verified successfully");
        return response;
    }

    @PutMapping("/reset-password")
    public Map<String, Object> resetPassword(@RequestBody Map<String, String> data) {
        Map<String, Object> response = new HashMap<>();

        String email = data.get("email");
        String otp = data.get("otp");
        String newPassword = data.get("newPassword");

        String storedOtp = otpStorage.get(email);

        if (storedOtp == null || !storedOtp.equals(otp)) {
            response.put("success", false);
            response.put("message", "Invalid OTP");
            return response;
        }

        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            response.put("success", false);
            response.put("message", "User not found");
            return response;
        }

        User user = userOptional.get();
        user.setPassword(newPassword);
        userRepository.save(user);

        otpStorage.remove(email);

        response.put("success", true);
        response.put("message", "Password reset successfully");
        return response;
    }
}