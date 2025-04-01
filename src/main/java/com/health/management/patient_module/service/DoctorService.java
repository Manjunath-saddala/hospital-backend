package com.health.management.patient_module.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.health.management.patient_module.model.Doctor;
import com.health.management.patient_module.repository.DoctorRepository;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Optional<Doctor> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }

    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public Doctor updateDoctor(Long id, Doctor updatedDoctor) {
        return doctorRepository.findById(id)
                .map(doctor -> {
                    doctor.setName(updatedDoctor.getName());
                    doctor.setSpecialization(updatedDoctor.getSpecialization());
                    doctor.setPhone(updatedDoctor.getPhone());
                    doctor.setEmail(updatedDoctor.getEmail());
                    return doctorRepository.save(doctor);
                })
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
}
