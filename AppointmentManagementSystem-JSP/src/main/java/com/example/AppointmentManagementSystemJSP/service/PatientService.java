package com.example.AppointmentManagementSystemJSP.service;

import com.example.AppointmentManagementSystemJSP.conversions.Conversions;
import com.example.AppointmentManagementSystemJSP.entity.*;
import com.example.AppointmentManagementSystemJSP.model.DoctorModel;
import com.example.AppointmentManagementSystemJSP.model.PatientModel;
import com.example.AppointmentManagementSystemJSP.repo.*;
import com.example.AppointmentManagementSystemJSP.serviceImplementation.patientInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService  implements patientInterface {
    @Autowired
    private PatientRepo patientRepo;
    @Autowired
    private DoctorRepo doctorRepo;
    @Autowired
    private DoctorScheduleRepo doctorScheduleRepo;
    @Autowired
    private AppointmentRepo appointmentRepo;
    @Autowired
    private WaitingRepo waitingRepo;
    @Autowired
    private Conversions conversions;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public Patient patientRegister(PatientModel patientModel){
        Patient patient=conversions.PatientModelToEntity(patientModel,new Patient());
        Patient patient1= patientRepo.findByUsername(patient.getUsername());
        if(patient1==null){
            return patientRepo.save(patient);
        }
        System.out.println(patient);
        return null;
    }
    public PatientModel searchPatient(String username){
        Patient patient= patientRepo.findByUsername(username);
        PatientModel patientModel=conversions.PatientEntityToModel(patient,new PatientModel());
        if(patientModel!=null)
            return patientModel;
        return null;
    }
    public boolean authenticate(String username,String password){
        Patient patient= patientRepo.findByUsername(username);
        if(patient!=null && encoder.matches(password,patient.getPassword())){
            return true;
        }
        return false;
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
    public List<DoctorModel>getDoctorsBasedOnSpecialtyAndLocation(String specialty,String location){
        List<Doctor> doctors=doctorRepo.findBySpecialtyAndLocation(specialty,location);
        List<DoctorModel> doctorModelList=new ArrayList<>();
        doctors.forEach(d->{
            DoctorModel doctorModel=conversions.DoctorEntityToModel(d,new DoctorModel());

            doctorModelList.add(doctorModel);

        });
        return doctorModelList;
    }

    public String cancelAppointment(Long patientId, Long appointmentId) {
        Optional<Appointment> optionalAppointment = appointmentRepo.findById(appointmentId);

        if (optionalAppointment.isPresent()) {
            Appointment appointment = optionalAppointment.get();
            Duration durationUntilStart = Duration.between(LocalDateTime.now(), appointment.getStartDateTime());
            if (durationUntilStart.toHours() < 5) {
                return "Appointment cannot be canceled at this stage";
             }

            if (appointment.getStatus() == AppointmentStatus.REQUESTED) {
                Doctor doctor = appointment.getDoctor();
                LocalDateTime startDateTime = appointment.getStartDateTime();
                LocalDateTime endDateTime = appointment.getEndDateTime();

                WaitingAppointment waitingAppointment = waitingRepo.findFirstByDoctorIdAndStartDateTimeAndEndDateTime(doctor.getId(), startDateTime, endDateTime);
                if (waitingAppointment != null) {
                    appointment.setType(waitingAppointment.getType());
                    appointment.setStatus(AppointmentStatus.REQUESTED);
                    appointment.setPatient(waitingAppointment.getPatient());
                    appointmentRepo.save(appointment);
                    waitingRepo.delete(waitingAppointment);

                    return "Appointment canceled successfully";
                }
                else {
                    DoctorSchedule doctorSchedule = doctorScheduleRepo.findByDoctorIdAndStartDateTimeAndEndDateTime(doctor.getId(), startDateTime, endDateTime);
                    doctorSchedule.setStatus(ScheduleStatus.UNBOOKED);
                    doctorScheduleRepo.save(doctorSchedule);

                    appointmentRepo.deleteById(appointmentId);

                    return "Appointment canceled successfully";
                }
            }
            else {
                return "Appointment cannot be canceled at this stage";
            }
        }
        return "Appointment not found";

    }



}
