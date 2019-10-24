package ru.lanit.demorest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.lanit.demorest.entity.Person;
import ru.lanit.demorest.repository.interfaces.PersonRepositoryInterface;
import ru.lanit.demorest.request.PersonSaveRequest;

import javax.validation.Valid;

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
                personSaveRequest.getBirthdate()
        ));

        return ResponseEntity.ok().build();
    }
}