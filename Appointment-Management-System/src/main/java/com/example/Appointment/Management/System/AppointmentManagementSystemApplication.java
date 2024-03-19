package com.example.Appointment.Management.System;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.Appointment.Management.System"})
public class AppointmentManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppointmentManagementSystemApplication.class, args);
	}

}
