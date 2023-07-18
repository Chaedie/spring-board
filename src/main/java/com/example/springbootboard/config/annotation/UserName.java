package com.example.springbootboard.config.annotation;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserNameValidator.class)
public @interface UserName {
    String message() default "유저네임은 특수문자를 제외한 4~16자리 소문자,숫자 조합이여야 합니다.";

    Class[] groups() default {};

    Class[] payload() default {};
}
