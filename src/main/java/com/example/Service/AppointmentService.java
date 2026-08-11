package com.example.Service;

import java.util.List;
import com.example.Bean.Appointment;

public interface AppointmentService {

    Appointment bookAppointment(
        Appointment appointment
    );

    List<Appointment> getAllAppointments();

    Appointment getAppointmentById(
        Long id
    );

    void cancelAppointment(Long id);

    void completeAppointment(Long id);
}