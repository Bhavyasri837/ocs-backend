package com.example.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Bean.Patient;
import com.example.Bean.User;
import com.example.Repository.PatientRepository;
import com.example.Repository.UserRepository;
import com.example.dto.ForgotPasswordRequest;
import com.example.dto.RegisterRequest;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PatientRepository patientRepo;

    @Override
    public Patient registerPatient(RegisterRequest request) {

        if (userRepo.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole("PATIENT");

        User savedUser = userRepo.save(user);

        Patient patient = new Patient();
        patient.setAge(request.getAge());
        patient.setGender(request.getGender());
        patient.setPhone(request.getPhone());
        patient.setAddress(request.getAddress());
        patient.setUser(savedUser);

        return patientRepo.save(patient);
    }
    @Override
    public void forgotPassword(
            ForgotPasswordRequest request) {

        User user = userRepo
                .findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Email not found"
                        ));

        user.setPassword(
                request.getNewPassword()
        );

        userRepo.save(user);
    }
}
