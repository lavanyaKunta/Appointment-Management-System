package com.example.AppointmentManagementSystemJSP.repo;

import com.example.AppointmentManagementSystemJSP.entity.Admin;
import com.example.AppointmentManagementSystemJSP.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo  extends JpaRepository<Admin,Long> {
    Admin findByUsername(String username);
}
