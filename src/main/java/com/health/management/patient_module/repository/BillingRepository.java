package com.health.management.patient_module.repository;


import com.health.management.patient_module.model.Billing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingRepository extends JpaRepository<Billing, Long> {
}
