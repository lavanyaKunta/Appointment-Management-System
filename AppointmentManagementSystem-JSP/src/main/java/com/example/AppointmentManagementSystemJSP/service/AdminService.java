package com.example.AppointmentManagementSystemJSP.service;

import com.example.AppointmentManagementSystemJSP.conversions.Conversions;
import com.example.AppointmentManagementSystemJSP.entity.*;
import com.example.AppointmentManagementSystemJSP.model.AdminModel;
import com.example.AppointmentManagementSystemJSP.model.DoctorModel;
import com.example.AppointmentManagementSystemJSP.model.PatientModel;
import com.example.AppointmentManagementSystemJSP.repo.*;
import com.example.AppointmentManagementSystemJSP.serviceImplementation.adminInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService implements adminInterface {
    @Autowired
    AdminRepo adminRepo;
    @Autowired
    DoctorService doctorService;
    @Autowired
    DoctorRepo doctorRepo;
    @Autowired
    PatientRepo patientRepo;
    @Autowired
    PatientService patientService;
    @Autowired
    DoctorScheduleRepo doctorScheduleRepo;
    @Autowired
    AppointmentRepo appointmentRepo;
    @Autowired
    Conversions conversions;


    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    public Admin adminRegister(AdminModel adminModel){
        Admin admin=conversions.AdminModelToEntity(adminModel,new Admin());
        Admin admin1= adminRepo.findByUsername(admin.getUsername());
        if(admin1==null){
            return adminRepo.save(admin);
        }
        return null;
    }
    public boolean authenticate(String username,String password){
        Admin admin= adminRepo.findByUsername(username);
        if(admin!=null && encoder.matches(password,admin.getPassword())){
            return true;
        }
        return false;
    }
    public AdminModel searchAdmin(String username){
        Admin admin= adminRepo.findByUsername(username);
        AdminModel adminModel=conversions.adminEntityToModel(admin,new AdminModel());
        if(adminModel!=null)
            return adminModel;
        return null;
    }
    public List<DoctorModel> getAllDoctors(){
        List<Doctor> doctors=doctorRepo.findAll();
        List<DoctorModel> doctorModelList=new ArrayList<>();
        doctors.forEach(d->{
            DoctorModel doctorModel=new DoctorModel();
            conversions.DoctorEntityToModel(d,doctorModel);
            doctorModelList.add(doctorModel);

        });
        return doctorModelList;
    }
    public List<PatientModel> getAllPatients(){
        List<Patient> patients=patientRepo.findAll();
        List<PatientModel> patientModelList=new ArrayList<>();
        patients.forEach(d->{
            PatientModel patientModel=new PatientModel();
            conversions.PatientEntityToModel(d,patientModel);
            patientModelList.add(patientModel);

        });
        return patientModelList;
    }
    public Optional<Doctor> removeDoctor(Long doctorId){
        Optional<Doctor> doctor=doctorRepo.findById(doctorId);
        List<DoctorSchedule> schedules=doctorScheduleRepo.findAllSchedulesByDoctorId(doctorId);
        List<Appointment>appointments=appointmentRepo.findByDoctorId(doctorId);

        doctorScheduleRepo.deleteAll(schedules);
        appointmentRepo.deleteAll(appointments);
        doctorRepo.deleteById(doctorId);
        return doctor;
    }
}
