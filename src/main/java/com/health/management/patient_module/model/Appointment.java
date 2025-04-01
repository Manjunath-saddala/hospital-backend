package com.health.management.patient_module.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "appointment")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    private String phone;
    private String email;
    private String reason;
    private LocalDateTime appointmentDate;

    // ✅ Add this to properly map nested doctor object in JSON
    @JsonProperty("doctor")
    public void setDoctorFromJson(Doctor doctor) {
        this.doctor = doctor;
    }
}
