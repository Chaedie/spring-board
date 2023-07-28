package com.example.springbootboard.config.annotation;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password {
    String message() default "비밀번호는 8~20자 영문 대 소문자, 숫자만 사용해주세요.";

    Class[] groups() default {};

    Class[] payload() default {};
}
