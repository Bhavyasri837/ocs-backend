package com.example.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Bean.Doctor;
import com.example.Repository.DoctorRepository;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository repo;

    @Override
    public Doctor addDoctor(Doctor doctor) {
        return repo.save(doctor);
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return repo.findAll();
    }

    @Override
    public Doctor getDoctorById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Doctor updateDoctor(Long id, Doctor doctor) {
        Doctor existing = repo.findById(id).orElse(null);

        if (existing != null) {
            existing.setName(doctor.getName());
            existing.setSpecialization(doctor.getSpecialization());
            existing.setEmail(doctor.getEmail());
            existing.setPhone(doctor.getPhone());
            existing.setExperience(doctor.getExperience());
            return repo.save(existing);
        }

        return null;
    }

    @Override
    public void deleteDoctor(Long id) {
        repo.deleteById(id);
    }
}