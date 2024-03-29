package com.example.springbootboard.config.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserNameValidator implements ConstraintValidator<UserName, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null || value.replaceAll(" ", "").equals("")) {
            return false;
        }

        return value.matches("^[a-z0-9-_.]{4,16}$");
    }
}
