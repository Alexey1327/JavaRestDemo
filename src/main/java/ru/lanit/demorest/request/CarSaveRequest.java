package ru.lanit.demorest.request;

import ru.lanit.demorest.request.validators.CarModelValid;
import ru.lanit.demorest.request.validators.CarNotExists;
import ru.lanit.demorest.request.validators.PersonExists;
import ru.lanit.demorest.request.validators.PersonIsAdult;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CarSaveRequest {

    @NotNull
    @CarNotExists
    private long id;

    @NotNull
    @CarModelValid
    private String model;

    @NotNull @Min(1)
    private int horsepower;

    @NotNull @PersonExists
    @PersonIsAdult
    private long ownerId;

    public long getId() {
        return id;
    }

    public CarSaveRequest setId(long id) {
        this.id = id;
        return this;
    }

    public String getModel() {
        return model;
    }

    public CarSaveRequest setModel(String model) {
        this.model = model;

        return this;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public CarSaveRequest setHorsepower(int horsepower) {
        this.horsepower = horsepower;

        return this;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public CarSaveRequest setOwnerId(long ownerId) {
        this.ownerId = ownerId;

        return this;
    }
}