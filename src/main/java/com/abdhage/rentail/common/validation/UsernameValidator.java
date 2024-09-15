package com.abdhage.rentail.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<Username, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;

        }
        final String REGEX_PATTERN = "^[a-z\\d](?:[a-z\\d]|-(?=[a-z\\d])){0,38}$";
        return value.matches(REGEX_PATTERN);
    }
}
