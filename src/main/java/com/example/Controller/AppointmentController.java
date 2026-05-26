package com.example.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.Bean.Appointment;
import com.example.Bean.Doctor;
import com.example.Bean.User;
import com.example.Repository.DoctorRepository;
import com.example.Repository.UserRepository;
import com.example.Service.AppointmentService;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "*")
public class AppointmentController {

    @Autowired
    private AppointmentService service;

    @Autowired
    private DoctorRepository doctorRepo;

    @Autowired
    private UserRepository userRepo;

    // ✅ Book appointment
    @PostMapping("/{userId}/{doctorId}")
    public Appointment bookAppointment(@PathVariable Long userId,
                                       @PathVariable Long doctorId,
                                       @RequestBody Appointment appointment) {

        Doctor doctor = doctorRepo.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        appointment.setDoctor(doctor);
        appointment.setUser(user);

        return service.bookAppointment(appointment);
    }

    // ✅ Get all
    @GetMapping
    public List<Appointment> getAllAppointments() {
        return service.getAllAppointments();
    }

    // ✅ Get by id
    @GetMapping("/{id}")
    public Appointment getById(@PathVariable Long id) {
        return service.getAppointmentById(id);
    }

    // ✅ Cancel
    @PutMapping("/cancel/{id}")
    public String cancel(@PathVariable Long id) {
        service.cancelAppointment(id);
        return "Appointment Cancelled";
    }

    // ✅ NEW: Complete appointment (missing in your code)
    @PutMapping("/complete/{id}")
    public String complete(@PathVariable Long id) {
        service.completeAppointment(id);
        return "Appointment Completed";
    }
}