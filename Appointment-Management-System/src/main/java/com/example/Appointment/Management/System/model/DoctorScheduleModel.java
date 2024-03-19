package com.example.Appointment.Management.System.model;

import com.example.Appointment.Management.System.entity.Doctor;
import com.example.Appointment.Management.System.validation.EndDateTimeAfterStartDateTime;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Component
@EndDateTimeAfterStartDateTime

public class DoctorScheduleModel {
    private Long id;
    @NotNull(message = "Doctor must not be null")
    private Doctor doctor;
    @NotNull(message = "Start date and time must not be null")
    @Future(message = "Start date and time must be in the future or present")
    private LocalDateTime startDateTime;
    @NotNull(message = "End date and time must not be null")

   @Future(message = "End date and time must be in the future")
    private LocalDateTime endDateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

}
