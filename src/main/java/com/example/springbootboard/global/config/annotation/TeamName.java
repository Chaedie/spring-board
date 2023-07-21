package com.example.springbootboard.global.config.annotation;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TeamNameValidator.class)
public @interface TeamName {
    String message() default "팀 이름은 2~20자 영문자, 숫자로 정해주세요.";

    Class[] groups() default {};

    Class[] payload() default {};
}
