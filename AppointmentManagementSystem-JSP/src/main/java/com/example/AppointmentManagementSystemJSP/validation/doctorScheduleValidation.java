package com.example.AppointmentManagementSystemJSP.validation;

import com.example.AppointmentManagementSystemJSP.model.DoctorModel;
import com.example.AppointmentManagementSystemJSP.model.DoctorScheduleModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.time.LocalDateTime;
@Component
public class doctorScheduleValidation implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        DoctorScheduleModel doctorScheduleModel=(DoctorScheduleModel) target;
        LocalDateTime currentDateTime = LocalDateTime.now();



        if (doctorScheduleModel.getStartDateTime() == null) {
            errors.rejectValue("startDateTime", "startDateTime.null", "Start date time must be provided");
            return;
        }
        if (doctorScheduleModel.getEndDateTime() == null) {
            errors.rejectValue("endDateTime", "endDateTime.null", "End date time must be provided");
            return;
        }

        if (doctorScheduleModel.getStartDateTime().isBefore(currentDateTime) || doctorScheduleModel.getEndDateTime().isBefore(currentDateTime)) {
            errors.rejectValue("startDateTime","datetime.invalid", "Datetime must be in the future.");
        }

        if (doctorScheduleModel.getStartDateTime().isAfter(doctorScheduleModel.getEndDateTime())) {
            errors.rejectValue("startDateTime", "startDateTime", "Start datetime must be before end datetime.");
        }

        if (doctorScheduleModel.getStartDateTime() == null || doctorScheduleModel.getEndDateTime() == null) {
            errors.reject("datetime.null", "Start datetime or end datetime cannot be null.");
            return;
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startDateTime", "startDateTime.empty","startDateTime should not empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endDateTime", "endDateTime.empty","EndDateTime should not empty");




    }
}
