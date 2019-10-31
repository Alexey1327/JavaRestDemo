package ru.lanit.demorest.intergration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.lanit.demorest.entity.Car;
import ru.lanit.demorest.repository.interfaces.CarRepositoryInterface;
import ru.lanit.demorest.repository.interfaces.PersonRepositoryInterface;
import ru.lanit.demorest.request.CarSaveRequest;

import javax.validation.Valid;

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
    public ResponseEntity carSaveAction(
            @Valid @RequestBody CarSaveRequest carSaveRequest
    ) {
        int dashIndex = carSaveRequest.getModel().indexOf('-');
        carRepository.saveCar(new Car(
                carSaveRequest.getId(),
                carSaveRequest.getModel().substring(0, dashIndex),
                carSaveRequest.getModel().substring(dashIndex + 1),
                carSaveRequest.getHorsepower(),
                personRepository.getById(carSaveRequest.getOwnerId())
        ));

        return ResponseEntity.ok().build();
    }
}
