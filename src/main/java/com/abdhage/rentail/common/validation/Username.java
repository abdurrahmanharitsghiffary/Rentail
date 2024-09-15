package com.abdhage.rentail.common.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;

@Constraint(validatedBy = UsernameValidator.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Documented
public @interface Username {
    String message() default "Starts with a lowercase letter or digit, may include lowercase letters, digits, or hyphens (not at start or end), and must be 1 to 39 characters long.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
