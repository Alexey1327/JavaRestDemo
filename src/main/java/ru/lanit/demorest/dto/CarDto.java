package ru.lanit.demorest.dto;

public class CarDto {

    private long id;

    private String vendor;

    private String model;

    private int horsepower;

    public CarDto(long id, String vendor, String model, int horsepower) {
        this.id = id;
        this.vendor = vendor;
        this.model = model;
        this.horsepower = horsepower;
    }

    public long getId() {
        return id;
    }

    public String getVendor() {
        return vendor;
    }

    public String getModel() {
        return model;
    }

    public int getHorsepower() {
        return horsepower;
    }
}
