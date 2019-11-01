package ru.lanit.demorest.request;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    public String getBirthdate() {

        return birthdate;
    }

    public PersonSaveRequest setId(Long id) {
        this.id = id;

        return this;
    }

    public PersonSaveRequest setName(String name) {
        this.name = name;

        return this;
    }

    public PersonSaveRequest setBirthdate(String birthdate) {
        this.birthdate = birthdate;

        return this;
    }
}
