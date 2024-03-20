package com.example.AppointmentManagementSystemJSP.repo;
import com.example.AppointmentManagementSystemJSP.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepo extends JpaRepository<Patient,Long> {

    Patient findByUsername(String username);
}
