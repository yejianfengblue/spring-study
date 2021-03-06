package com.yejianfengblue.spring;

import com.yejianfengblue.spring.bean.ValidatedBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author yejianfengblue
 */
@SpringBootTest
class BeanValidationTest {

    @Autowired
    private Validator validator;

    @Test
    void givenAnnotationNotNull_WhenSetNull_ThenErrorMustNotBeNull() {

        ValidatedBean validatedBean = new ValidatedBean();
        validatedBean.setNotNullString(null);
        Set<ConstraintViolation<ValidatedBean>> violations = validator.validate(validatedBean);
        Optional<ConstraintViolation<ValidatedBean>> notNullStringViolation = violations.stream()
                .filter(v -> v.getPropertyPath().toString().equals("notNullString"))
                .findFirst();
        assertTrue(notNullStringViolation.isPresent());
        assertEquals("notNullString", notNullStringViolation.get().getPropertyPath().toString());
        assertEquals("must not be null", notNullStringViolation.get().getMessage());
    }

    @Test
    void givenAnnotationIsNumber_WhenSetIllegalNumber_ThenErrorIsNotANumber() {

        ValidatedBean validatedBean = new ValidatedBean();
        validatedBean.setNumberString("abc");
        Set<ConstraintViolation<ValidatedBean>> violations = validator.validate(validatedBean);
        Optional<ConstraintViolation<ValidatedBean>> numberStringViolation = violations.stream()
                .filter(v -> v.getPropertyPath().toString().equals("numberString"))
                .findFirst();
        assertTrue(numberStringViolation.isPresent());
        assertEquals("numberString", numberStringViolation.get().getPropertyPath().toString());
        assertEquals("is not a number", numberStringViolation.get().getMessage());
    }

    @Test
    void givenAnnotationIsNumber_WhenSetNull_ThenNoError() {

        ValidatedBean validatedBean = new ValidatedBean();
        validatedBean.setNumberString(null);
        Set<ConstraintViolation<ValidatedBean>> violations = validator.validate(validatedBean);
        Optional<ConstraintViolation<ValidatedBean>> numberStringViolation = violations.stream()
                .filter(v -> v.getPropertyPath().toString().equals("numberString"))
                .findFirst();
        assertFalse(numberStringViolation.isPresent());
    }
}
