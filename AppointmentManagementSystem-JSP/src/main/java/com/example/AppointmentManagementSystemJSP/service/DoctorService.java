package com.example.AppointmentManagementSystemJSP.service;

import com.example.AppointmentManagementSystemJSP.conversions.Conversions;
import com.example.AppointmentManagementSystemJSP.entity.Doctor;
import com.example.AppointmentManagementSystemJSP.entity.DoctorSchedule;
import com.example.AppointmentManagementSystemJSP.entity.ScheduleStatus;
import com.example.AppointmentManagementSystemJSP.model.DoctorModel;
import com.example.AppointmentManagementSystemJSP.model.DoctorScheduleModel;
import com.example.AppointmentManagementSystemJSP.repo.AppointmentRepo;
import com.example.AppointmentManagementSystemJSP.repo.DoctorRepo;
import com.example.AppointmentManagementSystemJSP.repo.DoctorScheduleRepo;
import com.example.AppointmentManagementSystemJSP.serviceImplementation.doctorInterface;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorService implements doctorInterface {
    @Autowired
    private DoctorRepo doctorRepo;
    @Autowired
    private DoctorScheduleRepo doctorScheduleRepo;
    @Autowired
    private AppointmentRepo appointmentRepo;
    @Autowired
    private Conversions conversions;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public Doctor doctorRegister(DoctorModel doctorModel){
        Doctor doctor=conversions.DoctorModelToEntity(doctorModel,new Doctor());
        Doctor doctor1= doctorRepo.findByUsername(doctor.getUsername());
        if(doctor1==null){
            return doctorRepo.save(doctor);
        }
        System.out.println(doctor);
        return null;
    }
    public boolean authenticate(String username,String password){
        Doctor doctor= doctorRepo.findByUsername(username);
        if(doctor!=null && encoder.matches(password,doctor.getPassword())){
            return true;
        }
        return false;
    }
    public DoctorModel searchDoctor(String username){
        Doctor doctor= doctorRepo.findByUsername(username);
        DoctorModel doctorModel=conversions.DoctorEntityToModel(doctor,new DoctorModel());
        if(doctorModel!=null)
            return doctorModel;
        return null;
    }
    public DoctorSchedule createDoctorSchedule(DoctorScheduleModel doctorScheduleModel){

        Doctor doctor=doctorScheduleModel.getDoctor();
        DoctorSchedule doctorSchedule=conversions.DoctorScheduleModelToEntity(doctorScheduleModel,new DoctorSchedule());
        doctorSchedule.setStatus(ScheduleStatus.UNBOOKED);
        List<DoctorSchedule> existingSchedules=doctorScheduleRepo.findByDoctorIdAndStartDateTimeLessThanEqualAndEndDateTimeGreaterThanEqual(
                    doctorSchedule.getDoctor().getId(),
                    doctorSchedule.getStartDateTime(),
                    doctorSchedule.getEndDateTime());

        boolean isOverlapping = existingSchedules.stream()
                .anyMatch(existingSchedule ->
                        doctorSchedule.getStartDateTime().isBefore(existingSchedule.getEndDateTime()) &&
                                doctorSchedule.getEndDateTime().isAfter(existingSchedule.getStartDateTime()));

        if (isOverlapping) {
            return null;
        }

        return doctorScheduleRepo.save(doctorSchedule);
    }
    public List<DoctorScheduleModel>getDoctorSchedules(Long doctorId){
        List<DoctorSchedule>doctorSchedules=doctorScheduleRepo.findByDoctorId(doctorId);
        List<DoctorScheduleModel> doctorScheduleModelList=new ArrayList<>();
        doctorSchedules.forEach(d->{
            DoctorScheduleModel doctorScheduleModel=new DoctorScheduleModel();
            conversions.DoctorScheduleEntityToModel(d,doctorScheduleModel);
            doctorScheduleModelList.add(doctorScheduleModel);
        });
        return doctorScheduleModelList;
    }
    public List<DoctorScheduleModel> getDoctorUpComingSchedules(Long doctorId) {
        LocalDateTime currentDateTime = LocalDateTime.now();

        List<DoctorSchedule> allSchedules = doctorScheduleRepo.findByDoctorId(doctorId);
        List<DoctorSchedule> upcomingSchedules = allSchedules.stream()
                .filter(schedule -> schedule.getStartDateTime().isAfter(currentDateTime))
                .collect(Collectors.toList());

        List<DoctorScheduleModel> doctorScheduleModelList = new ArrayList<>();
        upcomingSchedules.forEach(d -> {
            DoctorScheduleModel doctorScheduleModel = new DoctorScheduleModel();
           BeanUtils.copyProperties(d, doctorScheduleModel);
            doctorScheduleModelList.add(doctorScheduleModel);
        });
        return doctorScheduleModelList;
    }
    public String cancelSchedule(Long doctorId,Long scheduleId){
        Optional<DoctorSchedule> doctorSchedule=doctorScheduleRepo.findById(scheduleId);
        if(doctorSchedule.get().getStatus()==ScheduleStatus.UNBOOKED){
            doctorScheduleRepo.deleteById(scheduleId);
            return "schedule deleted successfully";
        }
//        Appointment appointment=appointmentRepo.findByDoctorIdAndStartDateTimeAndEndDateTime(doctorId,doctorSchedule.get().getStartDateTime(),doctorSchedule.get().getEndDateTime());
        return "schedule is BOOKED, so u have to cancel Appointment";

    }


}

