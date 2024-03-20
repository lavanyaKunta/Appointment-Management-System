package com.example.AppointmentManagementSystemJSP.serviceImplementation;

import com.example.AppointmentManagementSystemJSP.entity.Patient;
import com.example.AppointmentManagementSystemJSP.model.DoctorModel;
import com.example.AppointmentManagementSystemJSP.model.PatientModel;

import java.util.List;

public interface patientInterface {
    Patient patientRegister(PatientModel patientModel);
    PatientModel searchPatient(String username);
    boolean authenticate(String username,String password);
    List<DoctorModel> getAllDoctors();
    List<DoctorModel>getDoctorsBasedOnSpecialtyAndLocation(String specialty,String location);
    String cancelAppointment(Long patientId,Long appointmentId);

}

