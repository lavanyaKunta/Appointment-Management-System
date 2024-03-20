package com.example.AppointmentManagementSystemJSP.validation;

import com.example.AppointmentManagementSystemJSP.model.DoctorModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
@Component
public class doctorValidation implements Validator {



    @Override
    public boolean supports(Class<?> clazz) {
        return DoctorModel.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        DoctorModel doctorModel=(DoctorModel) target;
        if(doctorModel.getUsername()!=null && (doctorModel.getUsername().length()<5)){
            errors.rejectValue("username","username","username at least 5 characters");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.empty","username should not empty");

        if(doctorModel.getPassword()!=null && doctorModel.getPassword().length()<5){
            errors.rejectValue("password","password");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password","password.empty","password should not empty");

        if(doctorModel.getName()!=null && doctorModel.getName().length()<3){
            errors.rejectValue("name","name");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name","name.empty","name should not empty");


   }



}
