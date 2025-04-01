package com.health.management.patient_module.service;

import com.health.management.patient_module.model.MedicalRecord;
import com.health.management.patient_module.model.Patient;
import com.health.management.patient_module.repository.MedicalRecordRepository;
import com.health.management.patient_module.repository.PatientRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final PatientRepository patientRepository;

    public MedicalRecordServiceImpl(MedicalRecordRepository medicalRecordRepository, PatientRepository patientRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public MedicalRecord createRecord(MedicalRecord record, Long patientId) {
        Optional<Patient> patientOpt = patientRepository.findById(patientId);

        if (patientOpt.isPresent()) {
            record.setPatient(patientOpt.get());
            record.setCreatedAt(new java.util.Date());
            record.setUpdatedAt(new java.util.Date());
            return medicalRecordRepository.save(record);
        } else {
            throw new IllegalArgumentException("Patient not found with ID: " + patientId);
        }
    }

    @Override
    public List<MedicalRecord> getAllRecords() {
        return medicalRecordRepository.findAll();
    }

    @Override
    public List<MedicalRecord> getRecordsByPatientId(Long patientId) {
        return medicalRecordRepository.findByPatientId(patientId);
    }

    @Override
    public MedicalRecord getRecordById(Long id) {
        return medicalRecordRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Medical record not found with ID: " + id));
    }
}
