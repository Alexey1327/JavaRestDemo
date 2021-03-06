package ru.lanit.demorest.request.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = CarModelValidator.class)
@Documented
public @interface CarModelValid {
    String message() default "Incorrect model";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
