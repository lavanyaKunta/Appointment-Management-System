package com.example.Appointment.Management.System.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

    @Target({ElementType.FIELD, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Constraint(validatedBy = EndDateTimeAfterStartDateTimeValidator.class)
    @ReportAsSingleViolation
    public @interface EndDateTimeAfterStartDateTime {
        String message() default "End date and time must be after start date and time";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }

