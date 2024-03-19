package com.example.Appointment.Management.System.repo;

import com.example.Appointment.Management.System.entity.Appointment;
import com.example.Appointment.Management.System.entity.Doctor;
import com.example.Appointment.Management.System.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment,Long> {
    List<Appointment> findByPatientAndDateTime(Patient patient, LocalDateTime dateTime);

    List<Appointment> findByDoctorAndDateTime(Doctor doctor, LocalDateTime dateTime);

    List<Appointment> findByPatientId(Long patientId);

    List<Appointment> findByPatientIdAndDateTimeAfter(Long patientId, LocalDateTime currentDateTime);

    List<Appointment> findByDoctorIdAndDateTimeAfter(Long doctorId, LocalDateTime currentDateTime);
}
