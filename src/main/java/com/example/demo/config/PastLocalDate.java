package com.example.demo.config;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.time.LocalDate;
import java.util.Locale;

/**
 * Created by wh on 2017/7/3.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PastLocalDate.PastValidator.class)
@Documented
public @interface PastLocalDate {

    String message() default "{javax.validation.constraints.Past.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


    class PastValidator implements ConstraintValidator<PastLocalDate,LocalDate>{

        @Override
        public void initialize(PastLocalDate pastLocalDate) {

        }

        @Override
        public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
            return localDate == null || localDate.isBefore(LocalDate.now());
        }
    }
}
