package com.health.management.patient_module.repository;

import com.health.management.patient_module.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    // ✅ Fetch appointments with the doctor's name using JOIN
    @Query("SELECT a FROM Appointment a JOIN FETCH a.doctor d")
    List<Appointment> findAllWithDoctor();
}
