package ru.lanit.demorest.repository.interfaces;

import ru.lanit.demorest.entity.Car;

public interface CarRepositoryInterface {

    void saveCar(Car car);

    Car getById(Long carId);

    Long countAll();

    Long uniqueVendorCountAll();

    void deleteAll();
}
