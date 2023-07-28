package com.example.springbootboard.config.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TeamNameValidator implements ConstraintValidator<TeamName, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null || value.replaceAll(" ", "").equals("")) {
            return false;
        }

        return value.matches("^[a-z0-9-_A-Z]{2,20}");
    }
}
