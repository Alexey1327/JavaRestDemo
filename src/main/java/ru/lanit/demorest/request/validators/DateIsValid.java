package ru.lanit.demorest.request.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, ElementType.PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = DateValidator.class)
@Documented
public @interface DateIsValid {
    String message() default "Invalid date, please use dd.MM.YYYY format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
