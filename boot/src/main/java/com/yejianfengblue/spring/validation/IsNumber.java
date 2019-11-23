package com.yejianfengblue.spring.validation;


import org.apache.commons.lang3.math.NumberUtils;

import java.lang.annotation.*;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

/**
 * The annotated string must be a valid Java number, which is be validated 
 * with {@link NumberUtils#isCreatable(String)}.
 * <p>
 * Accepts {@code String}. {@code null} elements are considered valid.
 * @author yejianfengblue
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { IsNumber.IsNumberValidator.class })
public @interface IsNumber {

    String message() default "is not a number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class IsNumberValidator implements ConstraintValidator<IsNumber, String> {

        @Override
        public void initialize(IsNumber constraintAnnotation) {}

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            return value == null || NumberUtils.isCreatable(value);
        }
    }
}