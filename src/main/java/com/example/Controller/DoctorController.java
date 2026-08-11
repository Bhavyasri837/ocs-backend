package com.example.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.Bean.Doctor;
import com.example.Service.DoctorService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService service;

    // Add doctor
    @PostMapping
    public Doctor addDoctor(@RequestBody Doctor doctor) {
        return service.addDoctor(doctor);
    }

    // Get all doctors
    @GetMapping
    public List<Doctor> getAllDoctors() {
        return service.getAllDoctors();
    }

    // Get doctor by ID
    @GetMapping("/{id}")
    public Doctor getDoctorById(@PathVariable Long id) {
        return service.getDoctorById(id);
    }

    // Update doctor
    @PutMapping("/{id}")
    public Doctor updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {
        return service.updateDoctor(id, doctor);
    }

    // Delete doctor
    @DeleteMapping("/{id}")
    public String deleteDoctor(@PathVariable Long id) {
        service.deleteDoctor(id);
        return "Doctor deleted successfully";
    }
}