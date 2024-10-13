package com.abdhage.rentail.common.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Documented
@Constraint(validatedBy = EnumValidatorImpl.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface EnumValidator {

    Class<? extends Enum<?>> enumClazz();

    String message() default "Value must be one of available enum type";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}