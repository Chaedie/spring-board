package com.example.springbootboard.global.config.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null || value.replaceAll(" ", "").equals("")) {
            return false;
        }

        return value.matches("([a-zA-Z0-9]).{7,20}");
    }
}
