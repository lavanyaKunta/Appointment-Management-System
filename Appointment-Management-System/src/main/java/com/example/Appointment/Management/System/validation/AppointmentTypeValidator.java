package com.example.Appointment.Management.System.validation;

import com.example.Appointment.Management.System.entity.AppointmentType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AppointmentTypeValidator implements ConstraintValidator<validAppointmentType,AppointmentType> {

    @Override
    public boolean isValid(AppointmentType value, ConstraintValidatorContext context) {
        return value!=null && (value== AppointmentType.CONSULTATION||value==AppointmentType.DIAGNOSTIC_TEST||value==AppointmentType.FOLLOW_UP||value==AppointmentType.ROUTINE_CHECKUP||value==AppointmentType.SURGERY||value==AppointmentType.OTHER);
    }
}
