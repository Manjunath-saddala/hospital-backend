package com.health.management.patient_module.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "appointments"})
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;               // Doctor's full name
    private String specialization;     // Doctor's field (e.g., Cardiology)
    private String phone;              // Contact number
    private String email;              // Email address
}


