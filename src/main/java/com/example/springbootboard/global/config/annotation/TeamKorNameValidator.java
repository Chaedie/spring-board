package com.example.springbootboard.global.config.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TeamKorNameValidator implements ConstraintValidator<TeamKorName, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null || value.replaceAll(" ", "").equals("")) {
            return false;
        }

        return value.matches("^[a-z0-9-_A-Z{Hangul}]{2,20}");
    }
}
