package com.eazybytes.cards.custom_annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target({PARAMETER, FIELD})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {CardNumberValidator.class})
public @interface ValidCardNumber {

    String message() default "Invalid card number. It must be 16 digits long.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
