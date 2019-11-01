package ru.lanit.demorest.intergration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.lanit.demorest.dto.CarDto;
import ru.lanit.demorest.dto.PersonDto;
import ru.lanit.demorest.entity.Car;
import ru.lanit.demorest.entity.Person;
import ru.lanit.demorest.repository.interfaces.PersonRepositoryInterface;
import ru.lanit.demorest.request.PersonSaveRequest;
import ru.lanit.demorest.request.validators.DateValidator;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonController {

    private PersonRepositoryInterface personRepository;

    @Autowired
    public PersonController(PersonRepositoryInterface personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping("/person")
    @Transactional
    public ResponseEntity personSaveAction(
            @Valid @RequestBody PersonSaveRequest personSaveRequest
    ) {
        personRepository.savePerson(new Person(
                personSaveRequest.getId(),
                personSaveRequest.getName(),
                LocalDate.parse(personSaveRequest.getBirthdate(), DateTimeFormatter.ofPattern(DateValidator.EUROPEAN_DATE_PATTERN))
        ));

        return ResponseEntity.ok().build();
    }

    @GetMapping("personwithcars")
    @ResponseBody
    @Transactional
    public ResponseEntity personAndCarsAction(@RequestParam Long personid)
    {
        Person person = personRepository.getById(personid);

        if (person == null) {
            return ResponseEntity.notFound().build();
        }

        List<CarDto> carDtoList = new ArrayList<>();
        for (Car car : person.getCarList()) {
            carDtoList.add(new CarDto(
                car.getId(),
                car.getVendor() + "-" + car.getModel(),
                car.getHorsePower()
            ));
        }

        return ResponseEntity.ok(new PersonDto(
                person.getId(),
                person.getName(),
                person.getBirthDate().format(DateTimeFormatter.ofPattern(DateValidator.EUROPEAN_DATE_PATTERN)),
                carDtoList
        ));
    }
}