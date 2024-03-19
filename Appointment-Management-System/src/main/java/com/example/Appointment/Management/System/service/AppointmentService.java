package com.example.Appointment.Management.System.service;

import com.example.Appointment.Management.System.entity.*;
import com.example.Appointment.Management.System.model.AppointmentModel;
import com.example.Appointment.Management.System.model.DoctorScheduleModel;
import com.example.Appointment.Management.System.repo.AppointmentRepo;
import com.example.Appointment.Management.System.repo.DoctorScheduleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepo appointmentRepo;
    @Autowired
    private DoctorScheduleRepo doctorScheduleRepo;
//    public Appointment createPatientAppointment(AppointmentModel appointmentModel){
//        Appointment appointment=AppointmentModelToEntity(appointmentModel,new Appointment());
//        LocalDateTime dateTime=appointment.getDateTime();
//        Patient patient=appointment.getPatient();
//
//        List<Appointment> existingAppointmentToPatient = appointmentRepo.findByPatientAndDateTime(patient, dateTime);
//        if(!existingAppointmentToPatient.isEmpty()){
//            throw new IllegalStateException("patient already has an appointment at the requested time");
//        }
//
//        Doctor doctor=appointment.getDoctor();
//        List<Appointment> existingAppointmentToDoctor = appointmentRepo.findByDoctorAndDateTime(doctor, dateTime);
//
//        if(existingAppointmentToDoctor.isEmpty()){
//            Long doctorId=doctor.getId();
//            List<DoctorSchedule> schedule=doctorScheduleRepo.findByDoctorId(doctorId);
//
//            boolean isDoctorAvailable = schedule.stream().anyMatch(s ->
//                    dateTime.isAfter(s.getStartDateTime()) && dateTime.isBefore(s.getEndDateTime()));
//
//            if (!isDoctorAvailable) {
//                throw new IllegalStateException("Doctor is not available");
//            }
//            else{
//                return appointmentRepo.save(appointment);
//            }
//
//        }
//        if(!existingAppointmentToDoctor.isEmpty()){
//            throw new IllegalStateException("doctor having appointments at this time");
//        }
//        return null;
//    }


    public String createPatientAppointment(AppointmentModel appointmentModel){
        Appointment appointment=AppointmentModelToEntity(appointmentModel,new Appointment());
        LocalDateTime dateTime=appointment.getDateTime();

        Patient patient=appointment.getPatient();
        List<Appointment> existingAppointmentToPatient = appointmentRepo.findByPatientAndDateTime(patient, dateTime);
        if(!existingAppointmentToPatient.isEmpty()){
            return "patient already has an appointment at the requested time";
        }

        Doctor doctor=appointment.getDoctor();
        List<Appointment> existingAppointmentToDoctor = appointmentRepo.findByDoctorAndDateTime(doctor, dateTime);
        if(existingAppointmentToDoctor.isEmpty()){
            Long doctorId=doctor.getId();
            List<DoctorSchedule> schedule=doctorScheduleRepo.findByDoctorId(doctorId);

            boolean isDoctorAvailable = schedule.stream().anyMatch(s ->
                    dateTime.isAfter(s.getStartDateTime()) && dateTime.isBefore(s.getEndDateTime()));

            if (!isDoctorAvailable) {
                return "Doctor is not available";
            }
            else{
                appointmentRepo.save(appointment);
                return "appointment created";
            }

        }
        if(!existingAppointmentToDoctor.isEmpty()){
            return "doctor having appointments at this time";
        }
        return null;
    }
    public List<AppointmentModel>getPatientAppointments(Long patientId){
        List<Appointment>patientAppointments=appointmentRepo.findByPatientId(patientId);
        List<AppointmentModel> appointmentModelList=new ArrayList<>();
        patientAppointments.forEach(d->{
            AppointmentModel appointmentModel=new AppointmentModel();
            AppointmentEntityToModel(d,appointmentModel);
            appointmentModelList.add(appointmentModel);
        });
        return appointmentModelList;
    }
    public List<AppointmentModel> getPatientUpComingAppointments(Long patientId){
        LocalDateTime currentDateTime=LocalDateTime.now();
        List<Appointment>patientAppointments=appointmentRepo.findByPatientIdAndDateTimeAfter(patientId,currentDateTime);
        List<AppointmentModel> appointmentModelList=new ArrayList<>();
        patientAppointments.forEach(d->{
            AppointmentModel appointmentModel=new AppointmentModel();
            AppointmentEntityToModel(d,appointmentModel);
            appointmentModelList.add(appointmentModel);
        });
        return appointmentModelList;
    }
    public List<AppointmentModel> getDoctorUpComingAppointments(Long doctorId){
        LocalDateTime currentDateTime=LocalDateTime.now();
        List<Appointment> doctorAppointments=appointmentRepo.findByDoctorIdAndDateTimeAfter(doctorId,currentDateTime);
        List<AppointmentModel> appointmentModelList=new ArrayList<>();
        doctorAppointments.forEach(d->{
            AppointmentModel appointmentModel=new AppointmentModel();
            AppointmentEntityToModel(d,appointmentModel);
            appointmentModelList.add(appointmentModel);
        });
        return appointmentModelList;
    }

    public void changeAppointmentStatus(Long appointmentId, AppointmentStatus newStatus) {
        Optional<Appointment> optionalAppointment = appointmentRepo.findById(appointmentId);
        if (optionalAppointment.isPresent()) {
            Appointment appointment = optionalAppointment.get();
            appointment.setStatus(newStatus);
            appointmentRepo.save(appointment);
        } else {
            throw new IllegalArgumentException("Appointment not found with ID: " + appointmentId);
        }
    }

    public Appointment AppointmentModelToEntity(AppointmentModel appointmentModel,Appointment appointment){
        appointment.setPatient(appointmentModel.getPatient());
        appointment.setDoctor(appointmentModel.getDoctor());
        appointment.setDateTime(appointmentModel.getDateTime());
        appointment.setStatus(appointmentModel.getStatus());
        appointment.setType(appointmentModel.getType());
        return appointment;
    }
    public AppointmentModel AppointmentEntityToModel(Appointment appointment,AppointmentModel appointmentModel){
        appointmentModel.setPatient(appointment.getPatient());
        appointmentModel.setDoctor(appointment.getDoctor());
        appointmentModel.setType(appointment.getType());
        appointmentModel.setStatus(appointment.getStatus());
        appointmentModel.setDateTime(appointment.getDateTime());
        return appointmentModel;
    }


}
