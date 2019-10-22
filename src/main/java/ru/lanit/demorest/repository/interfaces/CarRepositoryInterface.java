package ru.lanit.demorest.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lanit.demorest.entity.Car;

public interface CarRepositoryInterface extends JpaRepository<Car, Long> {

}
