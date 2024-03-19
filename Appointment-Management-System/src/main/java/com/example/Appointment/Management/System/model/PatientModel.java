package com.example.Appointment.Management.System.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class PatientModel {
    private Long id;
    @NotEmpty(message = "Name is required")
    private String name;
    @Positive(message = "Age must be a positive number")
    private int age;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
}
