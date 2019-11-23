package com.yejianfengblue.spring.validation;

import com.yejianfengblue.spring.formatter.Formatter;

import java.lang.annotation.*;
import java.time.format.DateTimeParseException;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

/**
 * The annotated string must have pattern {@link Formatter#FLT_DATE_PATTERN}.
 * <p>
 * Accepts {@code String}. {@code null} elements are considered valid.
 * @author yejianfengblue
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { IsFltDate.IsFltDateValidator.class })
public @interface IsFltDate {

    String message() default "is not a valid date in format '" + Formatter.FLT_DATE_PATTERN + "'";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class IsFltDateValidator implements ConstraintValidator<IsFltDate, String> {

        @Override
        public void initialize(IsFltDate constraintAnnotation) {}

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {

            if (null == value) {
                return true;
            } else {
                try {
                    Formatter.FLT_DATE_FORMATTER.parse(value);
                    return true;
                } catch (DateTimeParseException e) {
                    return false;
                }
            }
        }
    }
}