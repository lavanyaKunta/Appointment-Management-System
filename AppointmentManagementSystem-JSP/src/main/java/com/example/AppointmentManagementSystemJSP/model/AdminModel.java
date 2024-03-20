package com.example.AppointmentManagementSystemJSP.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class AdminModel {
    Long Id;
    @NotNull(message = "username should not be null")
    @NotBlank(message = "username should not be blank")
    private String username;
    @NotNull(message = "password should not be null")
    @NotBlank(message = "password should not be blank")
    private String password;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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
}
