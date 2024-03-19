package com.example.Appointment.Management.System.controller;

import com.example.Appointment.Management.System.entity.*;
import com.example.Appointment.Management.System.model.AppointmentModel;
import com.example.Appointment.Management.System.model.DoctorModel;
import com.example.Appointment.Management.System.model.DoctorScheduleModel;
import com.example.Appointment.Management.System.model.PatientModel;
import com.example.Appointment.Management.System.service.AppointmentService;
import com.example.Appointment.Management.System.service.DoctorScheduleService;
import com.example.Appointment.Management.System.service.DoctorService;
import com.example.Appointment.Management.System.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class controller {
    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private DoctorScheduleService doctorScheduleService;

//    @PostMapping("/registerPatient")
//    public String registerPatient(@Valid @RequestBody PatientModel patientModel){
//        Patient patient=patientService.registerPatient(patientModel);
////        if(patient==null)
////            return "already exist";
//        return "patient added";
//    }
    @PostMapping("/registerPatient")
    public ResponseEntity<PatientModel> registerPatient(@Valid @RequestBody PatientModel patientModel){
        PatientModel pat=patientService.registerPatient(patientModel);
        return new ResponseEntity<PatientModel>(pat, HttpStatus.CREATED);
    }
    @PostMapping("/registerDoctor")
    public String registerDoctor(@Valid @RequestBody DoctorModel doctorModel){
        Doctor doctor=doctorService.registerDoctor(doctorModel);
        return "doctor added";
    }
//    @PostMapping("/createDoctorSchedule")
//    public ResponseEntity<DoctorScheduleModel> createSchedule(@RequestBody DoctorScheduleModel doctorScheduleModel) {
//        DoctorSchedule createdSchedule = doctorScheduleService.createSchedule(doctorScheduleModel);
//        return new ResponseEntity<DoctorScheduleModel>(createdSchedule, HttpStatus.CREATED);
//    }
    @PostMapping("/createDoctorSchedule")
    public String createSchedule(@Valid @RequestBody DoctorScheduleModel doctorScheduleModel) {
        DoctorSchedule createdSchedule = doctorScheduleService.createSchedule(doctorScheduleModel);
        if(createdSchedule==null)
            return "schedule is already exist at this time";
        return "schedule created";
    }
    @GetMapping("/viewDoctors")
    public List<DoctorModel> getAllDoctors(){
        return doctorService.getAllDoctors();
    }
    @GetMapping("/viewDoctors/{specialty}/{location}")
    public List<DoctorModel>getAllDoctoresBasedOnSpecialty(@PathVariable("specialty")String specialty,@PathVariable("location")String location){
        return doctorService.getSpecialtyDoctors(specialty,location);
    }
   
    @GetMapping("viewDoctorSchedule/{doctorId}")
    public List<DoctorScheduleModel> viewDoctorSchedule(@PathVariable("doctorId")Long doctorId){
        System.out.println(doctorId);
        return doctorScheduleService.getDoctorSchedule(doctorId);
    }

    @GetMapping("viewDoctorCurrentDaySchedule/{doctorId}")
    public List<DoctorScheduleModel>viewCurrentDaySchedule(@PathVariable("doctorId")Long doctorId){
        return doctorScheduleService.getDoctorCurrentDaySchedule(doctorId);
    }
    @GetMapping("/viewAllDoctorsSchedules")
    public List<DoctorScheduleModel> getDoctorSchedule(){
        return doctorScheduleService.getAllDoctorsSchedules();
    }

    //----------------------
    @PostMapping("createPatientAppointment")
    public String createPatientAppointment(@Valid @RequestBody AppointmentModel appointmentModel){
        String str=appointmentService.createPatientAppointment(appointmentModel);
        if(str==null)
            return "appointment not created";
        return str;
    }
    @GetMapping("viewPatientAppointments/{patientId}")
    public List<AppointmentModel>viewPatientAppointments(@PathVariable("patientId")Long patientId){
        return appointmentService.getPatientAppointments(patientId);
    }
    @GetMapping("viewPatientUpComingAppointments/{patientId}")
    public List<AppointmentModel>viewPatientUpComingAppointments(@PathVariable("patientId")Long patientId){
        return appointmentService.getPatientUpComingAppointments(patientId);
    }
    @GetMapping("viewDoctorUpComingAppointments/{doctorId}")
    public List<AppointmentModel>viewDoctorUpComingAppointments(@PathVariable("doctorId")Long doctorId){
        return appointmentService.getDoctorUpComingAppointments(doctorId);
    }
    @PutMapping("appointments/{appointmentId}/status")
    public ResponseEntity<?> changeAppointmentStatus(@PathVariable Long appointmentId, @RequestParam("status") AppointmentStatus newStatus) {

        appointmentService.changeAppointmentStatus(appointmentId, newStatus);
        return ResponseEntity.ok("Appointment status updated successfully");
    }




}
