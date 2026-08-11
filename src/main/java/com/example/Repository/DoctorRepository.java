package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Bean.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}