package com.example.Appointment.Management.System.model;

import com.example.Appointment.Management.System.entity.AppointmentStatus;
import com.example.Appointment.Management.System.entity.AppointmentType;
import com.example.Appointment.Management.System.entity.Doctor;
import com.example.Appointment.Management.System.entity.Patient;
import com.example.Appointment.Management.System.validation.validAppointmentType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Component
public class AppointmentModel {
    private Long id;
    @NotNull(message = "Patient is required")
    private Patient patient;
    @NotNull(message = "Patient is required")
    private Doctor doctor;
    @Future(message = "Appointment date must be in the future")
    @NotNull(message = "Appointment date is required")
    private LocalDateTime dateTime;
    @NotNull
    @validAppointmentType(message = "please enter valid appointment type")
    private AppointmentType type;
    private AppointmentStatus status=AppointmentStatus.REQUESTED;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public AppointmentType getType() {
        return type;
    }

    public void setType(AppointmentType type) {
        this.type = type;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }
}
