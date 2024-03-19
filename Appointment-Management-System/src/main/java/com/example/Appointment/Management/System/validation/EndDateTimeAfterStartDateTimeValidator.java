package com.example.Appointment.Management.System.validation;

import com.example.Appointment.Management.System.model.DoctorScheduleModel;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.annotation.Annotation;
import java.time.LocalDateTime;

public class EndDateTimeAfterStartDateTimeValidator implements ConstraintValidator<EndDateTimeAfterStartDateTime,DoctorScheduleModel> {
    @Override
    public void initialize(EndDateTimeAfterStartDateTime constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(DoctorScheduleModel dto, ConstraintValidatorContext context) {
//        return false;

        LocalDateTime startDateTime = dto.getStartDateTime();
        LocalDateTime endDateTime = dto.getEndDateTime();

        // Add your validation logic here
//        System.out.println(startDateTime.isAfter(endDateTime));
//        System.out.println(endDateTime.isAfter(startDateTime));
        return endDateTime.isAfter(startDateTime);
    }
}
