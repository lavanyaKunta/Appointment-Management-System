package com.example.Appointment.Management.System.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Component
public class DoctorModel {
    private Long id;
    @NotBlank(message="Name is required")
    private String name;
    @NotBlank(message="specialty is required")
    private String specialty;
    @NotBlank(message="location is required")
    private String location;
//    @NotBlank(message="HospitalName is required")
    private String hospitalName;
    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "\\d{10}",message = "Mobile number must be 10 digits & allow only digits")
    private String mobileNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
