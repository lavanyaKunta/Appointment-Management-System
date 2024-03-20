package com.example.AppointmentManagementSystemJSP.service;

import com.example.AppointmentManagementSystemJSP.entity.Appointment;
import com.example.AppointmentManagementSystemJSP.entity.AppointmentStatus;
import com.example.AppointmentManagementSystemJSP.entity.Patient;
import com.example.AppointmentManagementSystemJSP.entity.Payment;
import com.example.AppointmentManagementSystemJSP.repo.AppointmentRepo;
import com.example.AppointmentManagementSystemJSP.repo.PatientRepo;
import com.example.AppointmentManagementSystemJSP.repo.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    AppointmentRepo appointmentRepo;
    @Autowired
    PatientRepo patientRepo;
    @Autowired
    PaymentRepo paymentRepo;
    public String makePayment(Long patientId,Long appointmentId){
        Optional<Appointment> appointment=appointmentRepo.findById(appointmentId);
        Patient patient=patientRepo.findById(patientId).orElse(null);
        Payment payment=new Payment();
        if(appointment.get().getStatus()== AppointmentStatus.CONFIRMED) {
            Payment payment1=paymentRepo.findByAppointmentId(appointmentId);
            if(payment1==null) {
                Appointment appointment2 = appointment.get();
                payment.setPatient(appointment.get().getPatient());
                payment.setAppointment(appointment2);
                payment.setMoney(appointment.get().getPayment());
                paymentRepo.save(payment);
                return " payment Done!!";
            }
            return "You already paid for this Appointment";
        }
        return null;
    }
}
