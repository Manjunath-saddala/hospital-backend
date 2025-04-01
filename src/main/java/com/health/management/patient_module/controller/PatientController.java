package com.health.management.patient_module.controller;

import com.health.management.patient_module.model.Patient;
import com.health.management.patient_module.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "http://localhost:3000")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        // ✅ Check that age and gender are included
        System.out.println("Received patient: " + patient);
        return patientRepository.save(patient);
    }

    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Patient updatePatient(@PathVariable Long id, @RequestBody Patient updatedPatient) {
        return patientRepository.findById(id)
                .map(patient -> {
                    patient.setFirstName(updatedPatient.getFirstName());
                    patient.setLastName(updatedPatient.getLastName());
                    patient.setEmail(updatedPatient.getEmail());
                    patient.setPhone(updatedPatient.getPhone());
                    patient.setAge(updatedPatient.getAge());          // ✅ Update age
                    patient.setGender(updatedPatient.getGender());    // ✅ Update gender
                    patient.setAddress(updatedPatient.getAddress());
                    return patientRepository.save(patient);
                })
                .orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id) {
        patientRepository.deleteById(id);
    }
}
