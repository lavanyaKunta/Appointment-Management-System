package com.example.AppointmentManagementSystemJSP.conversions;

import com.example.AppointmentManagementSystemJSP.entity.*;
import com.example.AppointmentManagementSystemJSP.model.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Conversions {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    public AdminModel adminEntityToModel(Admin admin, AdminModel adminModel){
        adminModel.setId(admin.getId());
        adminModel.setUsername(admin.getUsername());
        adminModel.setPassword(admin.getPassword());
        return adminModel;
    }
    public Admin AdminModelToEntity(AdminModel adminModel, Admin admin){
        admin.setUsername(adminModel.getUsername());
        admin.setPassword(encoder.encode(adminModel.getPassword()));
        return admin;
    }
    public Appointment AppointmentModelToEntity(AppointmentModel appointmentModel, Appointment appointment){
        appointment.setId(appointmentModel.getId());
        appointment.setPatient(appointmentModel.getPatient());
        appointment.setDoctor(appointmentModel.getDoctor());
        appointment.setStartDateTime(appointmentModel.getStartDateTime());
        appointment.setEndDateTime(appointmentModel.getEndDateTime());
        appointment.setStatus(appointmentModel.getStatus());
        appointment.setType(appointmentModel.getType());
        return appointment;
    }
    public AppointmentModel AppointmentEntityToModel(Appointment appointment,AppointmentModel appointmentModel){
        appointmentModel.setId(appointment.getId());
        appointmentModel.setPatient(appointment.getPatient());
        appointmentModel.setDoctor(appointment.getDoctor());
        appointmentModel.setType(appointment.getType());
        appointmentModel.setStatus(appointment.getStatus());
        appointmentModel.setStartDateTime(appointment.getStartDateTime());
        appointmentModel.setEndDateTime(appointment.getEndDateTime());
        return appointmentModel;
    }
    public Doctor DoctorModelToEntity(DoctorModel doctorModel, Doctor doctor){
        doctor.setUsername(doctorModel.getUsername());
        doctor.setPassword(encoder.encode(doctorModel.getPassword()));
        doctor.setName(doctorModel.getName());
        doctor.setSpecialty(doctorModel.getSpecialty());
        doctor.setLocation(doctorModel.getLocation());
        doctor.setHospitalName(doctorModel.getHospitalName());
        doctor.setMobileNo(doctorModel.getMobileNo());
        doctor.setAppointmentFee(doctorModel.getAppointmentFee());
        return doctor;
    }
    public DoctorModel DoctorEntityToModel(Doctor doctor,DoctorModel doctorModel){
        doctorModel.setId(doctor.getId());
        doctorModel.setUsername(doctor.getUsername());
        doctorModel.setPassword(doctor.getPassword());
        doctorModel.setSpecialty(doctor.getSpecialty());
        doctorModel.setName(doctor.getName());
        doctorModel.setLocation(doctor.getLocation());
        doctorModel.setHospitalName(doctor.getHospitalName());
        doctorModel.setMobileNo(doctor.getMobileNo());
        doctorModel.setAppointmentFee(doctor.getAppointmentFee());
        return doctorModel;
    }




    public DoctorSchedule DoctorScheduleModelToEntity(DoctorScheduleModel doctorScheduleModel, DoctorSchedule doctorSchedule){
        doctorSchedule.setId(doctorScheduleModel.getId());
        doctorSchedule.setDoctor(doctorScheduleModel.getDoctor());
        doctorSchedule.setStartDateTime(doctorScheduleModel.getStartDateTime());
        doctorSchedule.setEndDateTime(doctorScheduleModel.getEndDateTime());
        doctorSchedule.setStatus(doctorScheduleModel.getStatus());
        return doctorSchedule;
    }
    public DoctorScheduleModel DoctorScheduleEntityToModel(DoctorSchedule doctorSchedule,DoctorScheduleModel doctorScheduleModel){
        doctorScheduleModel.setDoctor(doctorSchedule.getDoctor());
        doctorScheduleModel.setId(doctorSchedule.getId());
        doctorScheduleModel.setStartDateTime(doctorSchedule.getStartDateTime());
        doctorScheduleModel.setEndDateTime(doctorSchedule.getEndDateTime());
        doctorScheduleModel.setStatus(doctorSchedule.getStatus());
        return doctorScheduleModel;
    }
    public Patient PatientModelToEntity(PatientModel patientModel, Patient patient){
        patient.setUsername(patientModel.getUsername());
        patient.setPassword(encoder.encode(patientModel.getPassword()));
        patient.setName(patientModel.getName());
        patient.setId(patientModel.getId());
        patient.setAge(patientModel.getAge());
        patient.setMobileNo(patientModel.getMobileNo());
        return patient;
    }
    public PatientModel PatientEntityToModel(Patient patient,PatientModel patientModel){
        patientModel.setUsername(patient.getUsername());
        patientModel.setPassword(patient.getPassword());
        patientModel.setName(patient.getName());
        patientModel.setId(patient.getId());
        patientModel.setAge(patient.getAge());
        patientModel.setMobileNo(patient.getMobileNo());
        return patientModel;
    }
}
