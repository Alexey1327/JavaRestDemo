package ru.lanit.demorest.request.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CarModelValidator implements ConstraintValidator<CarModelValid, String> {

    @Override
    public boolean isValid(String model, ConstraintValidatorContext constraintValidatorContext) {

        if (model == null){
            return false;
        }

        int dashIndex = model.indexOf('-');

        return dashIndex != 0
                && model.substring(dashIndex + 1).length() != 0;
    }
}
