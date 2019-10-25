package ru.lanit.demorest.dto;

public class CarDto {

    private long id;

    private String model;

    private int horsepower;

    public CarDto(long id, String model, int horsepower) {
        this.id = id;
        this.model = model;
        this.horsepower = horsepower;
    }

    public long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public int getHorsepower() {
        return horsepower;
    }
}
