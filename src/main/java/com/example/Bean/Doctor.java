package com.example.Bean;
import jakarta.persistence.*;
@Entity
@Table(name="doctor")
	public class Doctor {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String name;
	    private String specialization;
	    private String email;
	    private String phone;
	    private String experience;

	    public Doctor() {
	    }

	    public Doctor(Long id, String name, String specialization, String email, String phone, String experience) {
	        this.id = id;
	        this.name = name;
	        this.specialization = specialization;
	        this.email = email;
	        this.phone = phone;
	        this.experience = experience;
	    }

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSpecialization() {
			return specialization;
		}

		public void setSpecialization(String specialization) {
			this.specialization = specialization;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getExperience() {
			return experience;
		}

		public void setExperience(String experience) {
			this.experience = experience;
		}

	    // getters and setters
	    
	}