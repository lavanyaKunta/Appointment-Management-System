package com.example.AppointmentManagementSystemJSP.service;

import com.example.AppointmentManagementSystemJSP.conversions.Conversions;
import com.example.AppointmentManagementSystemJSP.entity.*;
import com.example.AppointmentManagementSystemJSP.model.AppointmentModel;
import com.example.AppointmentManagementSystemJSP.repo.AppointmentRepo;
import com.example.AppointmentManagementSystemJSP.repo.DoctorRepo;
import com.example.AppointmentManagementSystemJSP.repo.DoctorScheduleRepo;
import com.example.AppointmentManagementSystemJSP.repo.WaitingRepo;
import com.example.AppointmentManagementSystemJSP.serviceImplementation.appointmentInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService implements appointmentInterface {
    @Autowired
    private DoctorScheduleRepo doctorScheduleRepo;
    @Autowired
    private DoctorRepo doctorRepo;
    @Autowired
    private AppointmentRepo appointmentRepo;
    @Autowired
    private WaitingRepo waitingRepo;
    @Autowired
    private Conversions conversions;

    public Appointment getAppointment(Long appointmentId){
        return appointmentRepo.findById(appointmentId).orElse(null);
    }
    public String createAppointment(AppointmentModel appointmentModel,Long doctorId,Long patientId,Long scheduleId){
        Appointment appointment=conversions.AppointmentModelToEntity(appointmentModel,new Appointment());
        appointment.setStatus(AppointmentStatus.REQUESTED);
        DoctorSchedule doctorSchedule=doctorScheduleRepo.getScheduleById(scheduleId);

        Patient patient=appointment.getPatient();
        List<Appointment> existingAppointmentToPatient = appointmentRepo.findByPatientAndStartDateTimeAndEndDateTime(patient, doctorSchedule.getStartDateTime(),doctorSchedule.getEndDateTime());
        if(!existingAppointmentToPatient.isEmpty()){
            return "patient already has an appointment at the requested time";

        }
        Doctor doctor=appointment.getDoctor();
       
        if(doctorSchedule.getStatus()==ScheduleStatus.UNBOOKED) {
            doctorSchedule.setStatus(ScheduleStatus.BOOKED);
            doctorScheduleRepo.save(doctorSchedule);
            appointment.setStartDateTime(doctorSchedule.getStartDateTime());
            appointment.setEndDateTime(doctorSchedule.getEndDateTime());
            appointment.setPayment(doctor.getAppointmentFee());

            appointmentRepo.save(appointment);
            return null;
        }
        WaitingAppointment waitingAppointment = new WaitingAppointment();
        waitingAppointment.setPatient(appointment.getPatient());
        waitingAppointment.setDoctor(appointment.getDoctor());

        appointment.setStartDateTime(doctorSchedule.getStartDateTime());
        appointment.setEndDateTime(doctorSchedule.getEndDateTime());

        waitingAppointment.setStartDateTime(appointment.getStartDateTime());
        waitingAppointment.setEndDateTime(appointment.getEndDateTime());
        waitingAppointment.setType(appointment.getType());
        waitingAppointment.setStatus(AppointmentStatus.WAITING);
        waitingRepo.save(waitingAppointment);


        return "doctor schedule is already BOOKED, you added to waiting list";
    }
    public List<AppointmentModel>getDoctorUpComingAppointments(Long doctorId){
        LocalDateTime currentDateTime=LocalDateTime.now();
        List<Appointment> doctorAppointments=appointmentRepo.findByDoctorIdAndStartDateTimeAfter(doctorId,currentDateTime);
        List<AppointmentModel> appointmentModelList=new ArrayList<>();
        doctorAppointments.forEach(d->{
            AppointmentModel appointmentModel=new AppointmentModel();
            conversions.AppointmentEntityToModel(d,appointmentModel);
            appointmentModelList.add(appointmentModel);
        });
        return appointmentModelList;
    }
        public String changeAppointmentStatus(Long doctorId, Long appointmentId, AppointmentStatus status){
        Optional<Appointment> optionalAppointment = appointmentRepo.findById(appointmentId);

        if (optionalAppointment.isPresent()) {
            Appointment appointment = optionalAppointment.get();

            if (appointment.getStatus() == AppointmentStatus.REQUESTED && status == AppointmentStatus.CONFIRMED) {
                appointment.setStatus(status);
                appointmentRepo.save(appointment);
                return "Appointment status changed to CONFIRMED.";
            }
            else if (appointment.getStatus() == AppointmentStatus.REQUESTED && status == AppointmentStatus.REJECTED) {
                appointment.setStatus(status);
                appointmentRepo.save(appointment);
                return "Appointment status changed to REJECTED.";
            }
            else if (appointment.getStatus() == AppointmentStatus.CONFIRMED && status == AppointmentStatus.COMPLETED) {
                appointment.setStatus(status);
                appointmentRepo.save(appointment);
                return "Appointment status changed to COMPLETED.";
            }
        }
        return "Invalid status transition ";
    }



    public List<AppointmentModel>getPatientUpComingAppointments(Long patientId){
        LocalDateTime currentDateTime=LocalDateTime.now();
        List<Appointment> patientAppointments=appointmentRepo.findByPatientIdAndStartDateTimeAfter(patientId,currentDateTime);
        List<AppointmentModel> appointmentModelList=new ArrayList<>();
        patientAppointments.forEach(d->{
            AppointmentModel appointmentModel=new AppointmentModel();
            conversions.AppointmentEntityToModel(d,appointmentModel);
            appointmentModelList.add(appointmentModel);
        });
        return appointmentModelList;
    }

    public List<AppointmentModel>getPatientAppointments(Long patientId){
        List<Appointment> patientAppointments=appointmentRepo.findByPatientId(patientId);
        List<AppointmentModel> appointmentModelList=new ArrayList<>();
        patientAppointments.forEach(d->{
            AppointmentModel appointmentModel=new AppointmentModel();
            conversions.AppointmentEntityToModel(d,appointmentModel);
            appointmentModelList.add(appointmentModel);
        });
        return appointmentModelList;
    }

}
