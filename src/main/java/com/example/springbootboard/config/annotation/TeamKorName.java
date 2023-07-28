package com.example.springbootboard.config.annotation;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TeamKorNameValidator.class)
public @interface TeamKorName {
    String message() default "한글 팀 이름은 2~20자 한글, 숫자로 정해주세요.";

    Class[] groups() default {};

    Class[] payload() default {};
}
