package com.example.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Bean.Appointment;
import com.example.Bean.AppointmentStatus;
import com.example.Repository.AppointmentRepository;

@Service
public class AppointmentServiceImpl
        implements AppointmentService {

    @Autowired
    private AppointmentRepository repo;

    // ✅ BOOK APPOINTMENT
    @Override
    public Appointment bookAppointment(
            Appointment appointment) {

        // 🔴 Prevent past date booking
        if (
            appointment.getAppointmentDate()
            .isBefore(LocalDate.now())
        ) {

            throw new RuntimeException(
                "Cannot book appointment for past dates"
            );
        }

        // 🔴 Prevent duplicate slot booking
        boolean exists =
            repo.existsByDoctorAndAppointmentDateAndAppointmentTime(
                appointment.getDoctor(),
                appointment.getAppointmentDate(),
                appointment.getAppointmentTime()
            );

        if (exists) {

            throw new RuntimeException(
                "Slot already booked. Choose another slot."
            );
        }

        // ✅ Default appointment status
        appointment.setStatus(
            AppointmentStatus.BOOKED
        );

        return repo.save(appointment);
    }

    // ✅ GET ALL APPOINTMENTS
    @Override
    public List<Appointment> getAllAppointments() {

        return repo.findAll();
    }

    // ✅ GET APPOINTMENT BY ID
    @Override
    public Appointment getAppointmentById(
            Long id) {

        return repo.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Appointment not found"
                    )
                );
    }

    // ✅ CANCEL APPOINTMENT
    @Override
    public void cancelAppointment(Long id) {

        Appointment appt =
            repo.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Appointment not found"
                    )
                );

        // 🔴 Already cancelled
        if (
            appt.getStatus()
            == AppointmentStatus.CANCELLED
        ) {

            throw new RuntimeException(
                "Appointment already cancelled"
            );
        }

        // 🔴 Cannot cancel completed
        if (
            appt.getStatus()
            == AppointmentStatus.COMPLETED
        ) {

            throw new RuntimeException(
                "Cannot cancel completed appointment"
            );
        }

        // ✅ Update status
        appt.setStatus(
            AppointmentStatus.CANCELLED
        );

        repo.save(appt);
    }

    // ✅ COMPLETE APPOINTMENT
    @Override
    public void completeAppointment(
            Long id) {

        Appointment appt =
            repo.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Appointment not found"
                    )
                );

        // 🔴 Cannot complete cancelled
        if (
            appt.getStatus()
            == AppointmentStatus.CANCELLED
        ) {

            throw new RuntimeException(
                "Cannot complete cancelled appointment"
            );
        }

        // 🔴 Already completed
        if (
            appt.getStatus()
            == AppointmentStatus.COMPLETED
        ) {

            throw new RuntimeException(
                "Appointment already completed"
            );
        }

        // ✅ Update status
        appt.setStatus(
            AppointmentStatus.COMPLETED
        );

        repo.save(appt);
    }
}