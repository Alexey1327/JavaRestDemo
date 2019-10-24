package ru.lanit.demorest.request.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateValidator implements ConstraintValidator<DateIsValid, String> {

    public static final String EUROPEAN_DATE_PATTERN  = "dd.MM.yyyy";

    @Override
    public boolean isValid(String date, ConstraintValidatorContext constraintValidatorContext) {
        try {
            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(EUROPEAN_DATE_PATTERN));

            return localDate.isBefore(LocalDate.now());
        } catch (Exception e) {
            return false;
        }
    }
}