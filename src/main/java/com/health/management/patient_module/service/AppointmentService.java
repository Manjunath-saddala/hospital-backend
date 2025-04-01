package com.health.management.patient_module.service;

import com.health.management.patient_module.model.Appointment;
import com.health.management.patient_module.model.Doctor;
import com.health.management.patient_module.repository.AppointmentRepository;
import com.health.management.patient_module.repository.DoctorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private EmailService emailService;

    // ✅ Book appointment by doctor name
    @Transactional
    public Appointment bookAppointmentByDoctorName(Appointment appointment, String doctorName) {
        Optional<Doctor> doctorOpt = doctorRepository.findByName(doctorName);

        if (doctorOpt.isPresent()) {
            Doctor doctor = doctorOpt.get();
            appointment.setDoctor(doctor);

            // ✅ Save the appointment
            Appointment savedAppointment = appointmentRepository.save(appointment);

            // ✅ Send confirmation email
            emailService.sendAppointmentConfirmationEmail(savedAppointment);

            return savedAppointment;
        } else {
            throw new RuntimeException("Doctor not found with name: " + doctorName);
        }
    }

    // ✅ Retrieve all appointments with doctor name
    public List<Appointment> getAllAppointmentsWithDoctorName() {
        return appointmentRepository.findAllWithDoctor();
    }
}
