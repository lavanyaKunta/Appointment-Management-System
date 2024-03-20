package com.example.AppointmentManagementSystemJSP.validation;

import com.example.AppointmentManagementSystemJSP.model.DoctorModel;
import com.example.AppointmentManagementSystemJSP.model.PatientModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
@Component
public class patientValidation implements Validator {



    @Override
    public boolean supports(Class<?> clazz) {
        return PatientModel.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PatientModel patientModel=(PatientModel) target;
        if(patientModel.getUsername()!=null && (patientModel.getUsername().length()<5)){
            errors.rejectValue("username","username","username at least 5 characters");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.empty","username should not empty");

        if(patientModel.getPassword()!=null && patientModel.getPassword().length()<5){
            errors.rejectValue("password","password","password must be at least 5 characters");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password","password.empty","password should not empty");



    }



}
