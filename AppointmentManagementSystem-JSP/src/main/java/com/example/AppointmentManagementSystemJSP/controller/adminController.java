package com.example.AppointmentManagementSystemJSP.controller;

import com.example.AppointmentManagementSystemJSP.entity.Admin;
import com.example.AppointmentManagementSystemJSP.entity.Doctor;
import com.example.AppointmentManagementSystemJSP.model.AdminModel;
import com.example.AppointmentManagementSystemJSP.model.DoctorLoginModel;
import com.example.AppointmentManagementSystemJSP.model.DoctorModel;
import com.example.AppointmentManagementSystemJSP.model.PatientModel;
import com.example.AppointmentManagementSystemJSP.service.AdminService;
import com.example.AppointmentManagementSystemJSP.service.DoctorService;
import com.example.AppointmentManagementSystemJSP.validation.adminValidation;
import com.example.AppointmentManagementSystemJSP.validation.doctorValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class adminController {
    @Autowired
    private adminValidation adminValid;
    @Autowired
    private AdminService adminService;
    @Autowired
    private doctorValidation doctorValid;
    @Autowired
    private DoctorService doctorService;

    @RequestMapping("/adminRegisterForm")
    public String adminRegister(Model model){
        AdminModel adminModel=new AdminModel();
        model.addAttribute("adminModel",adminModel);
        return "adminRegisterForm";
    }

    @PostMapping("/registerAdmin")
    public String AdminRegister(@Valid @ModelAttribute("adminModel")AdminModel adminModel, BindingResult bindingResult, ModelMap model){
        adminValid.validate(adminModel,bindingResult);
        if(bindingResult.hasErrors()){
            return "adminRegisterForm";
        }
        Admin admin=adminService.adminRegister(adminModel);
        if(admin==null) {
            model.addAttribute("msg", "user already exist");
            return "adminRegisterForm";
        }
        AdminModel adminModel1=new AdminModel();
        model.addAttribute("adminModel",adminModel1);
        return "adminLoginForm";
    }
    @RequestMapping("/adminLoginForm")
    public String adminLoginForm(Model model){
        AdminModel adminModel=new AdminModel();
        model.addAttribute("adminModel",adminModel);
        return "adminLoginForm";
    }
    @PostMapping("/adminLogin")
    public String adminLogin(@Valid @ModelAttribute("adminModel") AdminModel adminModel, BindingResult bindingResult, ModelMap model){
        adminValid.validate(adminModel,bindingResult);
        if(bindingResult.hasErrors()){
            return "adminLoginForm";
        }
        if(adminService.authenticate(adminModel.getUsername(),adminModel.getPassword())){
            AdminModel adminModel1=adminService.searchAdmin(adminModel.getUsername());
            model.addAttribute("id",adminModel1.getId());
            return "adminDashboard";
        }
        else{
            return "redirect:/adminLoginForm";
        }
    }

    @RequestMapping("/doctorRegisterForm")
    public String doctorRegister(ModelMap model){
        DoctorModel doctorModel=new DoctorModel();
        model.addAttribute("doctorModel",doctorModel);
        return "doctorRegisterForm";
    }

    @PostMapping("/registerDoctor")
    public String DoctorRegister(@Valid @ModelAttribute("doctorModel")DoctorModel doctorModel, BindingResult bindingResult, ModelMap model){
        doctorValid.validate(doctorModel,bindingResult);
        if(bindingResult.hasErrors()){
            return "doctorRegisterForm";
        }
        Doctor doctor=doctorService.doctorRegister(doctorModel);
        if(doctor==null) {
            model.addAttribute("msg", "user already exist");
            return "doctorRegisterForm";
        }
        DoctorLoginModel doctorLoginModel=new DoctorLoginModel();
        model.addAttribute("doctorLoginModel",doctorLoginModel);
        return "adminDashboard";
    }

    @GetMapping("/adminViewDoctors")
    public String viewDoctors(ModelMap model){
        List<DoctorModel> doctors=adminService.getAllDoctors();
        model.addAttribute("doctors",doctors);
        return "AdminViewDoctors";
    }

    @GetMapping("/adminViewPatients")
    public String viewPatients(ModelMap model){
        List<PatientModel> patients=adminService.getAllPatients();
        model.addAttribute("patients",patients);
        return "adminViewPatients";
    }
    @RequestMapping("/removeDoctor")
    public String removeDoctor(@RequestParam("doctorId")Long doctorId,ModelMap model){
        Optional<Doctor> doctor=adminService.removeDoctor(doctorId);
        if(doctor!=null){
            model.addAttribute("msg","deleted successfully");
            return "AdminViewDoctors";
        }
        return null;
    }

}


