package com.example.AppointmentManagementSystemJSP.controller;

import com.example.AppointmentManagementSystemJSP.entity.Appointment;
import com.example.AppointmentManagementSystemJSP.entity.Doctor;
import com.example.AppointmentManagementSystemJSP.entity.DoctorSchedule;
import com.example.AppointmentManagementSystemJSP.entity.Patient;
import com.example.AppointmentManagementSystemJSP.model.*;
import com.example.AppointmentManagementSystemJSP.service.AppointmentService;
import com.example.AppointmentManagementSystemJSP.service.DoctorService;
import com.example.AppointmentManagementSystemJSP.service.PatientService;
import com.example.AppointmentManagementSystemJSP.service.PaymentService;
import com.example.AppointmentManagementSystemJSP.validation.patientLoginValidation;
import com.example.AppointmentManagementSystemJSP.validation.patientValidation;
import jakarta.validation.Valid;
import org.aspectj.weaver.patterns.AndPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class patientController {
    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private patientValidation patientValid;
    @Autowired
    private patientLoginValidation patientLoginValid;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private PaymentService paymentService;
    @RequestMapping("/patientRegisterForm")
    public String patientRegister(Model model){
        PatientModel patientModel=new PatientModel();
        model.addAttribute("patientModel",patientModel);
        return "patientRegisterForm";
    }

    @RequestMapping("/registerPatient")
    public String patientRegister(@Valid @ModelAttribute("patientModel")PatientModel patientModel, BindingResult bindingResult, ModelMap model){
        patientValid.validate(patientModel,bindingResult);
        if(bindingResult.hasErrors()){
            return "patientRegisterForm";
        }
        Patient patient=patientService.patientRegister(patientModel);
        if(patient==null) {
            model.addAttribute("msg", "user already exist");
            return "patientRegisterForm";
        }
        PatientLoginModel patientLoginModel=new PatientLoginModel();
        model.addAttribute("patientLoginModel",patientLoginModel);
        return "patientLoginForm";
    }
    @RequestMapping("/patientLoginForm")
    public String patientLoginForm(Model model){
        PatientLoginModel patientLoginModel=new PatientLoginModel();
        model.addAttribute("patientLoginModel",patientLoginModel);
        return "patientLoginForm";
    }
    @RequestMapping("/patientLogin")
    public String patientLogin(@Valid @ModelAttribute("patientLoginModel")PatientLoginModel patientLoginModel,BindingResult bindingResult,ModelMap model){
        patientLoginValid.validate(patientLoginModel,bindingResult);
        if(bindingResult.hasErrors()){
            //System.out.println(bindingResult);
            return "patientLoginForm";
        }
        if(patientService.authenticate(patientLoginModel.getUsername(),patientLoginModel.getPassword())){
            PatientModel patientModel=patientService.searchPatient(patientLoginModel.getUsername());
            model.addAttribute("id",patientModel.getId());
            return "patientDashboard";
        }
        else{
            return "redirect:/patientLoginForm";
        }
    }

    @RequestMapping("/viewDoctors")
    public String viewDoctors(@RequestParam("patientId")Long patientId,Model model){
        List<DoctorModel> doctors=patientService.getAllDoctors();
        model.addAttribute("doctors",doctors);
        model.addAttribute("patientId",patientId);
        return "viewDoctors";
    }
    @RequestMapping("/viewDoctorsBasedOnSpecialtyAndLocationForm")
    public String viewDoctorsBasedOnSpecialtyAndLocationForm(@RequestParam("patientId")Long patientId,Model model){
        model.addAttribute("patientId",patientId);
        return "choosingForm";
    }
    @RequestMapping("/viewDoctorsBasedOnSpecialtyAndLocation")
    public String viewDoctorsBasedOnSpecialtyAndLocation(@RequestParam("patientId")Long patientId,@RequestParam("specialty")String specialty,@RequestParam("location")String location, Model model) {
        List<DoctorModel> doctors=patientService.getDoctorsBasedOnSpecialtyAndLocation(specialty,location);
        model.addAttribute("doctors",doctors);
        model.addAttribute("patientId",patientId);
        System.out.println("doctorsss");
        return "viewDoctors";
    }
    @RequestMapping("/viewDoctorUpComingSchedules")
    public String viewDoctorUpComingSchedules(@RequestParam("patientId")Long patientId,@RequestParam("doctorId")Long doctorId,ModelMap model){
        List<DoctorScheduleModel> schedules=doctorService.getDoctorUpComingSchedules(doctorId);
        System.out.println(schedules);

        model.addAttribute("schedules",schedules);
        model.addAttribute("patientId",patientId);
        model.addAttribute("doctorId",doctorId);
        return "viewSchedules2";
    }
    @RequestMapping("/createAppointment")
    public String createAppointmentModel(@RequestParam("patientId")Long patientId,@RequestParam("doctorId")Long doctorId,@RequestParam("scheduleId")Long scheduleId, ModelMap model){
        model.addAttribute("patientId",patientId);
        model.addAttribute("doctorId",doctorId);
        model.addAttribute("scheduleId",scheduleId);
        model.addAttribute("appointmentModel",new AppointmentModel());
        return "createAppointment";
    }

    @RequestMapping("/submitAppointment")
    public String appointment(@ModelAttribute("appointmentModel")AppointmentModel appointmentModel,BindingResult bindingResult, @RequestParam("id")Long patientId,@RequestParam("id")Long doctorId,@RequestParam("id")Long scheduleId, ModelMap model){
        String msg=appointmentService.createAppointment(appointmentModel,doctorId,patientId,scheduleId);
        if(msg==null){
            model.addAttribute("msg2","AppointmentCreated successfully");
            return "appointmentCreated";
        }
        model.addAttribute("msg",msg);
        return "viewSchedules2";
    }

    @RequestMapping("/viewPatientUpComingAppointments")
    public String viewPatientUpComingAppointments(@RequestParam("patientId")Long patientId,ModelMap model){
        List<AppointmentModel>appointments=appointmentService.getPatientUpComingAppointments(patientId);
        if(appointments.isEmpty()){
            model.addAttribute("msg","no appointments found");
        }
        model.addAttribute("appointments",appointments);
        model.addAttribute("id",patientId);
        return "viewPatientUpComingAppointments";
    }
    @RequestMapping("/viewPatientAppointments")
    public String viewPatientAppointments(@RequestParam("patientId")Long patientId,ModelMap model){
        List<AppointmentModel>appointments=appointmentService.getPatientAppointments(patientId);
        if(appointments.isEmpty()){
            model.addAttribute("msg","no appointments found");
        }
        model.addAttribute("appointments",appointments);
        model.addAttribute("id",patientId);
        return "viewPatientAppointments";
    }
    @RequestMapping("/cancelAppointment")
    public String cancelAppointment(@RequestParam("patientId")Long patientId,@RequestParam("id")Long appointmentId, ModelMap model){
        String msg=patientService.cancelAppointment(patientId,appointmentId);
        model.addAttribute("msg",msg);
        return "viewPatientUpComingAppointments";
    }
    @RequestMapping("/makePayment")
    public String makePayment(@RequestParam("patientId")Long patientId,@RequestParam("appointmentId")Long appointmentId,ModelMap model){
        Appointment appointment=appointmentService.getAppointment(appointmentId);
        if(appointment!=null) {
            model.addAttribute("patientId", patientId);
            model.addAttribute("appointment", appointment);
            return "payment";
        }
        return "appointment not found with this Id";
    }
    @RequestMapping("paymentDone")
    public String paymentDone(@RequestParam("patientId")Long patientId,@RequestParam("appointmentId")Long appointmentId,ModelMap model){
        String msg=paymentService.makePayment(patientId,appointmentId);
        Appointment appointment= appointmentService.getAppointment(appointmentId);
        if(msg!=null){
            model.addAttribute("msg",msg);
            model.addAttribute("appointment",appointment);
            return "payment";
        }
        model.addAttribute("msg","AppointmentStatus is REQUESTED, payment Not Accepted at this Stage");
        return "payment";
    }



}
