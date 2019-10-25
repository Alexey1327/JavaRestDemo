package ru.lanit.demorest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.lanit.demorest.repository.interfaces.CarRepositoryInterface;
import ru.lanit.demorest.repository.interfaces.PersonRepositoryInterface;

import javax.transaction.Transactional;

@RestController
public class ClearController {

    private PersonRepositoryInterface personRepository;

    private CarRepositoryInterface carRepository;

    @Autowired
    public ClearController(
            PersonRepositoryInterface personRepository,
            CarRepositoryInterface carRepository
    ) {
        this.personRepository = personRepository;
        this.carRepository = carRepository;
    }

    @GetMapping("/clear")
    @ResponseBody
    @Transactional
    public ResponseEntity clearAction() {

        carRepository.deleteAll();
        personRepository.deleteAll();

        return ResponseEntity.ok().build();
    }
}