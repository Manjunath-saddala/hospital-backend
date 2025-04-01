package com.health.management.patient_module.controller;

import com.health.management.patient_module.model.Appointment;
import com.health.management.patient_module.service.AppointmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // ✅ POST: Book appointment by Doctor's name
    @PostMapping
    public ResponseEntity<String> createAppointment(@RequestBody Appointment appointment) {

        // ✅ Validate doctor object properly
        if (appointment.getDoctor() == null || appointment.getDoctor().getName() == null || appointment.getDoctor().getName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("❌ Doctor name is required in the request payload.");
        }

        String doctorName = appointment.getDoctor().getName();

        try {
            Appointment savedAppointment = appointmentService.bookAppointmentByDoctorName(appointment, doctorName);
            return ResponseEntity.ok("✅ Appointment booked successfully with Dr. " + savedAppointment.getDoctor().getName() + "!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("❌ " + e.getMessage());
        }
    }

    // ✅ GET: Retrieve all appointments with Doctor Name
    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointmentsWithDoctorName();
    }
}
