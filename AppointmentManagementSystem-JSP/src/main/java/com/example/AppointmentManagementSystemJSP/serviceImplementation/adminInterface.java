package com.example.AppointmentManagementSystemJSP.serviceImplementation;

import com.example.AppointmentManagementSystemJSP.entity.Admin;
import com.example.AppointmentManagementSystemJSP.entity.Doctor;
import com.example.AppointmentManagementSystemJSP.model.AdminModel;
import com.example.AppointmentManagementSystemJSP.model.DoctorModel;
import com.example.AppointmentManagementSystemJSP.model.PatientModel;

import java.util.List;
import java.util.Optional;

public interface adminInterface {
    Admin adminRegister(AdminModel adminModel);
    boolean authenticate(String username,String password);
    AdminModel searchAdmin(String username);
    List<DoctorModel> getAllDoctors();
    List<PatientModel> getAllPatients();
    Optional<Doctor> removeDoctor(Long doctorId);
}
