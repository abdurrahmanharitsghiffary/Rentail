package com.abdhage.rentail.common.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

@Constraint(validatedBy = PasswordValidator.class)
@Documented
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {
    String message() default "Password must be at least 8 characters long and include at least one uppercase letter, one lowercase letter, one number, and one special character (@, #, $, %, ^, &, +, =).";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
