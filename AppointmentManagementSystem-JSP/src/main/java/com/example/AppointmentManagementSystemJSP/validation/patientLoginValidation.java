package com.example.AppointmentManagementSystemJSP.validation;

import com.example.AppointmentManagementSystemJSP.model.DoctorLoginModel;
import com.example.AppointmentManagementSystemJSP.model.PatientLoginModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
@Component
public class patientLoginValidation implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return PatientLoginModel.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PatientLoginModel patientLoginModel=(PatientLoginModel) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.empty","username should not empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password","password.empty","password should not empty");

    }
}
