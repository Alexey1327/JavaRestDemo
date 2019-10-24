package ru.lanit.demorest.dto;

import java.util.List;

public class PersonDto {

    private long id;

    private String name;

    private String birthdate;

    private List<CarDto> cars;

    public PersonDto(long id, String name, String birthdate, List<CarDto> cars) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.cars = cars;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public List<CarDto> getCars() {
        return cars;
    }
}