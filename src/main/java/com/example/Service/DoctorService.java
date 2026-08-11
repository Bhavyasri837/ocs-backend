package com.example.Service;

import java.util.List;
import com.example.Bean.*;

public interface DoctorService {

    Doctor addDoctor(Doctor doctor);

    List<Doctor> getAllDoctors();

    Doctor getDoctorById(Long id);

    Doctor updateDoctor(Long id, Doctor doctor);

    void deleteDoctor(Long id);
}