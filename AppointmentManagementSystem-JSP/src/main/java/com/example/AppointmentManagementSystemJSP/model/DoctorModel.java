package com.example.AppointmentManagementSystemJSP.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Component
public class DoctorModel {
    private Long id;
    @NotBlank(message="username is required")
    private String username;
    @NotBlank(message="password is required")
    private String password;
    @NotBlank(message="Name is required")
    private String name;
    @Size(min=1,message = "choose specialty")
    private String specialty;
    @Size(min=1,message = "choose location")
    private String location;
    @Size(min=1,message = "choose hospitalName")
    private String hospitalName;
    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^(?!0)\\d{10}$",message = "Mobile number must be 10 digits & allow only digits & not stars with 0")
    private String mobileNo;
    @NotNull(message = "appointmentFee is required")
    private Double appointmentFee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Double getAppointmentFee() {
        return appointmentFee;
    }

    public void setAppointmentFee(Double appointmentFee) {
        this.appointmentFee = appointmentFee;
    }
}
