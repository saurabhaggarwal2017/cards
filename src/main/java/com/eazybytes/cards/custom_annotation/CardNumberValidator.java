package com.eazybytes.cards.custom_annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CardNumberValidator implements ConstraintValidator<ValidCardNumber, Long> {

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return Long.toString(value).length() == 16;
    }
}
