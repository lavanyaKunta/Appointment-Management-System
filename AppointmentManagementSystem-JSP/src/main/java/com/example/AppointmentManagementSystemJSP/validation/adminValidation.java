package com.example.AppointmentManagementSystemJSP.validation;

import com.example.AppointmentManagementSystemJSP.model.AdminModel;
import com.example.AppointmentManagementSystemJSP.model.PatientLoginModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
@Component
public class adminValidation implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return AdminModel.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AdminModel adminModel=(AdminModel) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.empty","username should not empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password","password.empty","password should not empty");

    }
}
