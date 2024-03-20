package com.example.AppointmentManagementSystemJSP.serviceImplementation;

import com.example.AppointmentManagementSystemJSP.entity.Doctor;
import com.example.AppointmentManagementSystemJSP.entity.DoctorSchedule;
import com.example.AppointmentManagementSystemJSP.model.DoctorModel;
import com.example.AppointmentManagementSystemJSP.model.DoctorScheduleModel;

import java.util.List;

public interface doctorInterface {
    Doctor doctorRegister(DoctorModel doctorModel);
    boolean authenticate(String username,String password);
    DoctorModel searchDoctor(String username);
    DoctorSchedule createDoctorSchedule(DoctorScheduleModel doctorScheduleModel);
    List<DoctorScheduleModel> getDoctorSchedules(Long doctorId);
    List<DoctorScheduleModel> getDoctorUpComingSchedules(Long doctorId);

}
