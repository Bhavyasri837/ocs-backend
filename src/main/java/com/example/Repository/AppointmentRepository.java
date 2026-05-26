package com.example.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Bean.Appointment;
import com.example.Bean.Doctor;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    // 🔴 Prevent duplicate booking
    boolean existsByDoctorAndAppointmentDateAndAppointmentTime(
            Doctor doctor,
            LocalDate appointmentDate,
            LocalTime appointmentTime
    );
}
