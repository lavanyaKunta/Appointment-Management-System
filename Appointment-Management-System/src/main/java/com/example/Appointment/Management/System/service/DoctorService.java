package com.example.Appointment.Management.System.service;

import com.example.Appointment.Management.System.entity.Doctor;
import com.example.Appointment.Management.System.entity.Patient;
import com.example.Appointment.Management.System.model.DoctorModel;
import com.example.Appointment.Management.System.model.PatientModel;
import com.example.Appointment.Management.System.repo.DoctorRepo;
import com.example.Appointment.Management.System.repo.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {
    @Autowired
    DoctorRepo doctorRepo;
    public Doctor registerDoctor(DoctorModel doctorModel){
        Doctor doctor=DoctorModelToEntity(doctorModel,new Doctor());
        return doctorRepo.save(doctor);
    }
    public List<DoctorModel>getAllDoctors(){
        List<Doctor> doctor=doctorRepo.findAll();
        List<DoctorModel> doctorModelList=new ArrayList<>();
        doctor.forEach(d->{
            DoctorModel doctorModel=new DoctorModel();
            doctorEntityToModel(d,doctorModel);
            doctorModelList.add(doctorModel);
        });
        return doctorModelList;
    }
    public List<DoctorModel> getSpecialtyDoctors(String specialty,String location){
        List<Doctor> doctor=doctorRepo.findBySpecialtyAndLocation(specialty,location);
        List<DoctorModel> doctorModelList=new ArrayList<>();
        doctor.forEach(d->{
            DoctorModel doctorModel=new DoctorModel();
            doctorEntityToModel(d,doctorModel);
            doctorModelList.add(doctorModel);
        });
        return doctorModelList;
    }


    public Doctor DoctorModelToEntity(DoctorModel doctorModel,Doctor doctor){
        doctor.setName(doctorModel.getName());
        doctor.setSpecialty(doctorModel.getSpecialty());
        doctor.setLocation(doctorModel.getLocation());
        doctor.setHospitalName(doctorModel.getHospitalName());
        doctor.setMobileNo(doctorModel.getMobileNo());
        return doctor;
    }
    public DoctorModel doctorEntityToModel(Doctor doctor,DoctorModel doctorModel){
        doctorModel.setId(doctor.getId());
        doctorModel.setSpecialty(doctor.getSpecialty());
        doctorModel.setName(doctor.getName());
        doctorModel.setLocation(doctor.getLocation());
        doctorModel.setHospitalName(doctor.getHospitalName());
        doctorModel.setMobileNo(doctor.getMobileNo());
        return doctorModel;
    }

}
