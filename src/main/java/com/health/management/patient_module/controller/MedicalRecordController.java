package com.health.management.patient_module.controller;

import com.health.management.patient_module.model.MedicalRecord;
import com.health.management.patient_module.service.MedicalRecordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/medical-records")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @PostMapping
    public ResponseEntity<MedicalRecord> createRecord(
            @RequestParam Long patientId,
            @RequestBody MedicalRecord record) {

        MedicalRecord createdRecord = medicalRecordService.createRecord(record, patientId);
        return ResponseEntity.ok(createdRecord);
    }

    @GetMapping
    public ResponseEntity<List<MedicalRecord>> getAllRecords() {
        List<MedicalRecord> records = medicalRecordService.getAllRecords();
        return ResponseEntity.ok(records);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<MedicalRecord>> getRecordsByPatientId(@PathVariable Long patientId) {
        List<MedicalRecord> records = medicalRecordService.getRecordsByPatientId(patientId);
        return ResponseEntity.ok(records);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalRecord> getRecordById(@PathVariable Long id) {
        MedicalRecord record = medicalRecordService.getRecordById(id);
        return ResponseEntity.ok(record);
    }
}
