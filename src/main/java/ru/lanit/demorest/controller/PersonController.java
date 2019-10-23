package ru.lanit.demorest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lanit.demorest.entity.Person;
import ru.lanit.demorest.repository.interfaces.PersonRepositoryInterface;
import ru.lanit.demorest.request.PersonSaveRequest;

import javax.servlet.ServletException;
import java.sql.Date;

@RestController
public class PersonController {

    private PersonRepositoryInterface personRepository;

    @Autowired
    public PersonController(PersonRepositoryInterface personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping("/person")
    public ResponseEntity personSaveAction(
        @RequestBody PersonSaveRequest personSaveRequest
    ) throws ServletException {


        Person person = new Person(
                (long) 1,
            "Aleksey",
                Date.valueOf("1990-03-01")
        );
        personRepository.savePerson(person);

        return ResponseEntity.ok().build();
    }

}