package com.example.AppointmentManagementSystemJSP.repo;



import com.example.AppointmentManagementSystemJSP.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor,Long> {

    List<Doctor> findBySpecialtyAndLocation(String specialty, String location);


    Doctor findByUsername(String username);
}
