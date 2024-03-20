package com.example.AppointmentManagementSystemJSP.model;

import com.example.AppointmentManagementSystemJSP.entity.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Component
public class WaitingAppointmentModel {
    private Long id;
    @NotNull(message = "Patient is required")
    private Patient patient;
    @NotNull(message = "doctor is required")
    private Doctor doctor;
    @Future(message = "Appointment date must be in the future")
    @NotNull(message = "Appointment date is required")
    private LocalDateTime startDateTime;
    @Future(message = "Appointment date must be in the future")
    @NotNull(message = "Appointment date is required")
    private LocalDateTime endDateTime;
    @NotNull(message = "appointment type should not be null")

    private AppointmentType type;
    private AppointmentStatus status=AppointmentStatus.WAITING;

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

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
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
