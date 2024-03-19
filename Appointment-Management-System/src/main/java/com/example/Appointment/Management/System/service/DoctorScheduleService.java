package com.example.Appointment.Management.System.service;

import com.example.Appointment.Management.System.entity.Doctor;
import com.example.Appointment.Management.System.entity.DoctorSchedule;
import com.example.Appointment.Management.System.model.DoctorModel;
import com.example.Appointment.Management.System.model.DoctorScheduleModel;
import com.example.Appointment.Management.System.repo.DoctorScheduleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorScheduleService {
    @Autowired
    private DoctorScheduleRepo doctorScheduleRepo;
    public DoctorSchedule createSchedule(DoctorScheduleModel doctorScheduleModel){
        DoctorSchedule doctorSchedule=DoctorScheduleModelToEntity(doctorScheduleModel,new DoctorSchedule());

//        LocalDateTime dateTime=doctorSchedule.getDateTime();
//        Doctor doctor=doctorSchedule.getDoctor();
//
//        List<DoctorSchedule> existingSchedules = doctorScheduleRepo.findByDoctorAndDateTime(doctor, dateTime);

        List<DoctorSchedule> existingSchedules = doctorScheduleRepo.findByDoctorIdAndStartDateTimeLessThanEqualAndEndDateTimeGreaterThanEqual(
                doctorSchedule.getDoctor().getId(),
                doctorSchedule.getStartDateTime(),
                doctorSchedule.getEndDateTime());
        for (DoctorSchedule existingSchedule : existingSchedules) {
            if (doctorSchedule.getStartDateTime().isBefore(existingSchedule.getEndDateTime()) &&
                    doctorSchedule.getEndDateTime().isAfter(existingSchedule.getStartDateTime())) {
                // Overlapping schedule found, reject creation
                return null;
            }
        }

//        if(!existingSchedules.isEmpty()){
//            return null;
//        }

//        if (Duration.between(doctorSchedule.getStartDateTime(), doctorSchedule.getEndDateTime()).toMinutes() < 10) {
//            // Minimum duration requirement not met, reject creation
//            return null;


            return doctorScheduleRepo.save(doctorSchedule);
    }
    public List<DoctorScheduleModel>getAllDoctorsSchedules(){
        List<DoctorSchedule>doctorsSchedules=doctorScheduleRepo.findAll();
        List<DoctorScheduleModel> doctorScheduleModelList=new ArrayList<>();
        doctorsSchedules.forEach(d->{
            DoctorScheduleModel doctorScheduleModel=new DoctorScheduleModel();
            DoctorScheduleEntityToModel(d,doctorScheduleModel);
            doctorScheduleModelList.add(doctorScheduleModel);
        });
        return doctorScheduleModelList;
    }
    public List<DoctorScheduleModel>getDoctorSchedule(Long doctorId){
        List<DoctorSchedule>doctorSchedules=doctorScheduleRepo.findByDoctorId(doctorId);
        List<DoctorScheduleModel> doctorScheduleModelList=new ArrayList<>();
        doctorSchedules.forEach(d->{
            DoctorScheduleModel doctorScheduleModel=new DoctorScheduleModel();
            DoctorScheduleEntityToModel(d,doctorScheduleModel);
            doctorScheduleModelList.add(doctorScheduleModel);
        });
        return doctorScheduleModelList;
    }
    public List<DoctorScheduleModel> getDoctorCurrentDaySchedule(Long doctorId) {
        LocalDate currentDate=LocalDate.now();
        LocalDateTime startOfDay = currentDate.atStartOfDay();
        LocalDateTime endOfDay = currentDate.atTime(23, 59, 59);
        List<DoctorSchedule> doctorSchedules = doctorScheduleRepo.findByDoctorIdAndStartDateTimeBetween(doctorId, startOfDay, endOfDay);
        List<DoctorScheduleModel> doctorScheduleModelList=new ArrayList<>();
        doctorSchedules.forEach(d->{
            DoctorScheduleModel doctorScheduleModel=new DoctorScheduleModel();
            DoctorScheduleEntityToModel(d,doctorScheduleModel);
            doctorScheduleModelList.add(doctorScheduleModel);
        });
        return doctorScheduleModelList;


    }
    public DoctorSchedule DoctorScheduleModelToEntity(DoctorScheduleModel doctorScheduleModel,DoctorSchedule doctorSchedule){
        doctorSchedule.setId(doctorScheduleModel.getId());
        doctorSchedule.setDoctor(doctorScheduleModel.getDoctor());
        doctorSchedule.setStartDateTime(doctorScheduleModel.getStartDateTime());
        doctorSchedule.setEndDateTime(doctorScheduleModel.getEndDateTime());
        return doctorSchedule;
    }
    public DoctorScheduleModel DoctorScheduleEntityToModel(DoctorSchedule doctorSchedule,DoctorScheduleModel doctorScheduleModel){
        doctorScheduleModel.setDoctor(doctorSchedule.getDoctor());
        doctorScheduleModel.setId(doctorSchedule.getId());
        doctorScheduleModel.setStartDateTime(doctorSchedule.getStartDateTime());
        doctorScheduleModel.setEndDateTime(doctorSchedule.getEndDateTime());
        return doctorScheduleModel;
    }



}
