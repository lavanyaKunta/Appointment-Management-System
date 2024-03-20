package com.example.AppointmentManagementSystemJSP.serviceImplementation;

import com.example.AppointmentManagementSystemJSP.entity.Appointment;
import com.example.AppointmentManagementSystemJSP.entity.AppointmentStatus;
import com.example.AppointmentManagementSystemJSP.model.AppointmentModel;

import java.util.List;

public interface appointmentInterface {
    String createAppointment(AppointmentModel appointmentModel, Long doctorId, Long patientId, Long scheduleId);
    List<AppointmentModel> getDoctorUpComingAppointments(Long doctorId);
    String changeAppointmentStatus(Long doctorId, Long appointmentId, AppointmentStatus status);
    List<AppointmentModel>getPatientUpComingAppointments(Long patientId);
    List<AppointmentModel>getPatientAppointments(Long patientId);

}
