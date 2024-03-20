package com.example.AppointmentManagementSystemJSP.controller;

import com.example.AppointmentManagementSystemJSP.entity.AppointmentStatus;
import com.example.AppointmentManagementSystemJSP.entity.Doctor;
import com.example.AppointmentManagementSystemJSP.entity.DoctorSchedule;
import com.example.AppointmentManagementSystemJSP.model.AppointmentModel;
import com.example.AppointmentManagementSystemJSP.model.DoctorLoginModel;
import com.example.AppointmentManagementSystemJSP.model.DoctorModel;
import com.example.AppointmentManagementSystemJSP.model.DoctorScheduleModel;
import com.example.AppointmentManagementSystemJSP.repo.DoctorScheduleRepo;
import com.example.AppointmentManagementSystemJSP.service.AppointmentService;
import com.example.AppointmentManagementSystemJSP.service.DoctorService;
import com.example.AppointmentManagementSystemJSP.validation.doctorLoginValidation;
import com.example.AppointmentManagementSystemJSP.validation.doctorScheduleValidation;
import com.example.AppointmentManagementSystemJSP.validation.doctorValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class doctorController {
    @Autowired
    private doctorValidation doctorValid;
    @Autowired
    private doctorLoginValidation doctorLoginValid;
    @Autowired
    private doctorScheduleValidation doctorScheduleValid;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private AppointmentService appointmentService;
    @RequestMapping("/home")
    public String home(){
        return "home";
    }

    @RequestMapping("/doctorLoginForm")
    public String doctorLoginForm(Model model){
        DoctorLoginModel doctorLoginModel=new DoctorLoginModel();
        model.addAttribute("doctorLoginModel",doctorLoginModel);
        return "doctorLoginForm";
    }
    @RequestMapping("/doctorLogin")
    public String doctorLogin(@Valid @ModelAttribute("doctorLoginModel")DoctorLoginModel doctorLoginModel,BindingResult bindingResult,ModelMap model){
        doctorLoginValid.validate(doctorLoginModel,bindingResult);
        if(bindingResult.hasErrors()){
            //System.out.println(bindingResult);
            return "doctorLoginForm";
        }
        if(doctorService.authenticate(doctorLoginModel.getUsername(),doctorLoginModel.getPassword())){
            DoctorModel doctorModel=doctorService.searchDoctor(doctorLoginModel.getUsername());
            model.addAttribute("id",doctorModel.getId());
            return "doctorDashboard";
        }
        else{
            return "redirect:/doctorLoginForm";
        }
    }

    @RequestMapping("/createDoctorSchedule")
    public String createDoctorSchedule(Long doctorId,ModelMap map){
        map.addAttribute("id",doctorId);
        map.addAttribute("doctorScheduleModel",new DoctorScheduleModel());
        return "createDoctorSchedule";
    }
    @RequestMapping("/submitSchedule")
    public String doctorSchedule(@Valid @ModelAttribute("doctorScheduleModel") DoctorScheduleModel doctorScheduleModel,BindingResult bindingResult,ModelMap model) {
        System.out.println(doctorScheduleModel);
        doctorScheduleValid.validate(doctorScheduleModel,bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("id",doctorScheduleModel.getDoctor().getId());

            System.out.println(bindingResult.getAllErrors());
            return "createDoctorSchedule";
        }
        model.addAttribute("id",doctorScheduleModel.getDoctor().getId());

        DoctorSchedule doctorSchedule = doctorService.createDoctorSchedule(doctorScheduleModel);
        if(doctorSchedule == null) {
            System.out.println("doctor schedule null");
            model.addAttribute("msg","schedule Already Exists");
            return "createDoctorSchedule";

        }
        model.addAttribute("msg2","scheduleCreated successfully");
        return "scheduleCreated";

    }
    @RequestMapping("/viewSchedules")
    public String viewSchedules(@RequestParam("doctorId")Long doctorId, Model model){
        List<DoctorScheduleModel> schedules=doctorService.getDoctorSchedules(doctorId);
        if(schedules.isEmpty()){
            model.addAttribute("msg","no schedules found");
        }
        model.addAttribute("schedules",schedules);
        return "viewSchedules";
    }
    @RequestMapping("/viewUpcomingSchedules")
    public String viewUpComingSchedules(@RequestParam("doctorId")Long doctorId, Model model){
        List<DoctorScheduleModel> schedules=doctorService.getDoctorUpComingSchedules(doctorId);
        if(schedules.isEmpty()){
            model.addAttribute("msg","no schedules found");
        }
        model.addAttribute("schedules",schedules);
        return "viewUpcomingSchedules";
    }
    @RequestMapping("/viewDoctorUpComingAppointments")
    public String viewDoctorUpComingAppointments(@RequestParam("doctorId")Long doctorId,ModelMap model){
        List<AppointmentModel>appointments=appointmentService.getDoctorUpComingAppointments(doctorId);
        if(appointments.isEmpty()){
            model.addAttribute("msg","no appointments found");
        }
        model.addAttribute("appointments",appointments);
        model.addAttribute("id",doctorId);
        return "viewDoctorUpComingAppointments";
    }
    @RequestMapping("/changeAppointmentStatus")
    public String changeAppointmentStatus(@RequestParam("doctorId") Long doctorId, @RequestParam("appointmentId")Long appointmentId, @RequestParam("status") AppointmentStatus status,ModelMap model) {
        String str=appointmentService.changeAppointmentStatus(doctorId,appointmentId,status);
        model.addAttribute("msg",str);
        return  "statusPage";
    }
    @RequestMapping("/cancelSchedule")
    public String cancelSchedule(@RequestParam("doctorId")Long doctorId,@RequestParam("scheduleId") Long id,ModelMap model){
        String str=doctorService.cancelSchedule(doctorId,id);
        model.addAttribute("msg",str);
        return "viewUpcomingSchedules";
    }



}
