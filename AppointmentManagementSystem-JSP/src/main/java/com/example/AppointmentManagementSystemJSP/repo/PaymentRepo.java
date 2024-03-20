package com.example.AppointmentManagementSystemJSP.repo;

import com.example.AppointmentManagementSystemJSP.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepo extends JpaRepository<Payment,Long> {
    Payment findByAppointmentId(Long appointmentId);
}
