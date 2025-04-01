package com.health.management.patient_module.service;

import com.health.management.patient_module.model.Patient;
import com.health.management.patient_module.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    // Create a new patient
    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    // Get all patients
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // Get a patient by ID
    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    // Update patient by ID
    public Patient updatePatient(Long id, Patient updatedPatient) {
        Patient patient = patientRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Patient not found"));

        patient.setFirstName(updatedPatient.getFirstName());
        patient.setLastName(updatedPatient.getLastName());
        patient.setEmail(updatedPatient.getEmail());
        patient.setPhone(updatedPatient.getPhone());
        patient.setAge(updatedPatient.getAge());
        patient.setGender(updatedPatient.getGender());
        patient.setAddress(updatedPatient.getAddress());

        return patientRepository.save(patient);
    }

    // Delete patient by ID
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}
