package com.example.AppointmentManagementSystemJSP.repo;


import com.example.AppointmentManagementSystemJSP.entity.Appointment;
import com.example.AppointmentManagementSystemJSP.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment,Long> {

    List<Appointment> findByPatientAndStartDateTimeAndEndDateTime(Patient patient, LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<Appointment> findByDoctorIdAndStartDateTimeAfter(Long doctorId, LocalDateTime currentDateTime);

    List<Appointment> findByPatientIdAndStartDateTimeAfter(Long patientId, LocalDateTime currentDateTime);

    List<Appointment> findByPatientId(Long patientId);

    List<Appointment> findByDoctorId(Long doctorId);

    Appointment findByDoctorIdAndStartDateTimeAndEndDateTime(Long doctorId, LocalDateTime startDateTime, LocalDateTime endDateTime);



}
