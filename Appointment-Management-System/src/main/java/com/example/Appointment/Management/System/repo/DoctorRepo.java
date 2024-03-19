package com.example.Appointment.Management.System.repo;

import com.example.Appointment.Management.System.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor,Long> {
//    List<Doctor> findBySpecialty(String specialty);

    List<Doctor> findBySpecialtyAndLocation(String specialty, String location);
}
