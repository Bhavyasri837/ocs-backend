package com.example.Bean;
import java.time.LocalDate;
import java.time.LocalTime;
import jakarta.persistence.*;
@Entity
@Table(name="apoointment")
public class Appointment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name="user_id",nullable=false)
	private User user;
	@ManyToOne
	@JoinColumn(name="doctor_id",nullable=false)
	private Doctor doctor;
	private LocalDate appointmentDate;
	private LocalTime appointmentTime;
	
	@Enumerated(EnumType.STRING)
	private AppointmentStatus status;
	public Appointment() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user2) {
		this.user = user2;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor2) {
		this.doctor = doctor2;
	}

	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public LocalTime getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(LocalTime appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public AppointmentStatus getStatus() {
		return status;
	}

	public void setStatus(AppointmentStatus status) {
		this.status = status;
	}

}
