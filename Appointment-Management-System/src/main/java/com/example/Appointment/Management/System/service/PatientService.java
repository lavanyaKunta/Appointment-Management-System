package com.example.Appointment.Management.System.service;

import com.example.Appointment.Management.System.entity.Patient;
import com.example.Appointment.Management.System.model.PatientModel;
import com.example.Appointment.Management.System.repo.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    @Autowired
    PatientRepo patientRepo;
    public PatientModel registerPatient(PatientModel patientModel){
        Patient patient=patientModelToEntity(patientModel,new Patient());
//        Patient pat=patientRepo.findById(patient.getId()).orElse(null);
//        if(pat==null){
             patientRepo.save(patient);
//        }
//       return null;
        return patientModel;

    }
    public Patient patientModelToEntity(PatientModel patientModel,Patient patient){
        patient.setName(patientModel.getName());
        patient.setId(patientModel.getId());
        patient.setAge(patientModel.getAge());
        patient.setMobileNo(patientModel.getMobileNo());
        return patient;
    }
}
