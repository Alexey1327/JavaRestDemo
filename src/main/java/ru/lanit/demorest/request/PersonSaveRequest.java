package ru.lanit.demorest.request;

import ru.lanit.demorest.request.validators.DateIsValid;
import ru.lanit.demorest.request.validators.DateValidator;
import ru.lanit.demorest.request.validators.PersonNotExists;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PersonSaveRequest {

    @NotNull
    @PersonNotExists
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @DateIsValid
    private String birthdate;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthdate() {
        return LocalDate.parse(birthdate, DateTimeFormatter.ofPattern(DateValidator.EUROPEAN_DATE_PATTERN));
    }
}
