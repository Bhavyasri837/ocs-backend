package com.example.Service;

import com.example.Bean.Patient;
import com.example.dto.ForgotPasswordRequest;
import com.example.dto.RegisterRequest;

public interface AuthService {
    Patient registerPatient(RegisterRequest request);
    void forgotPassword(
    	    ForgotPasswordRequest request
    	);
}