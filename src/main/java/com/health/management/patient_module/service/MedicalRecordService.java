package com.health.management.patient_module.service;

import com.health.management.patient_module.model.MedicalRecord;

import java.util.List;

public interface MedicalRecordService {

    MedicalRecord createRecord(MedicalRecord record, Long patientId);

    List<MedicalRecord> getAllRecords();

    List<MedicalRecord> getRecordsByPatientId(Long patientId);

    MedicalRecord getRecordById(Long id);
}
