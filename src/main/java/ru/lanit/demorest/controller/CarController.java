package ru.lanit.demorest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lanit.demorest.entity.Car;
import ru.lanit.demorest.entity.Person;
import ru.lanit.demorest.repository.interfaces.CarRepositoryInterface;
import ru.lanit.demorest.repository.interfaces.PersonRepositoryInterface;

import javax.servlet.ServletException;

@RestController
public class CarController {

    private PersonRepositoryInterface personRepository;

    private CarRepositoryInterface carRepository;

    @Autowired
    public CarController(
            PersonRepositoryInterface personRepository,
            CarRepositoryInterface carRepository
    ) {
        this.personRepository = personRepository;
        this.carRepository = carRepository;
    }

    @PostMapping("/car")
    @Transactional
    public ResponseEntity carSaveAction() throws ServletException {
        int personId = 1;

        Person person = personRepository.getById((long)personId);

        if (person == null) {
            throw new ServletException("Person not found");
        }

        Car car = new Car(
                (long)1,
                "BMW",
                "X5",
                157,
                person
        );
        carRepository.saveCar(car);

        return ResponseEntity.ok().build();
    }

//    @GetMapping("personwithcars")
//    public ResponseEntity personAndCarsAction()
//    {
//        return ResponseEntity.ok().build();
//    }
}
