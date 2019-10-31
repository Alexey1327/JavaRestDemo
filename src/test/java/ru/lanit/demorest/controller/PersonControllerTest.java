package ru.lanit.demorest.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.lanit.demorest.config.AppConfig;
import ru.lanit.demorest.entity.Car;
import ru.lanit.demorest.entity.Person;
import ru.lanit.demorest.repository.interfaces.CarRepositoryInterface;
import ru.lanit.demorest.repository.interfaces.PersonRepositoryInterface;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= {AppConfig.class})
@WebAppConfiguration
public class PersonControllerTest {

    @Autowired
    private PersonRepositoryInterface personRepository;

    @Autowired
    private CarRepositoryInterface carRepository;

    MockMvc mockMvc;

//    @BeforeEach
//    void setup(WebApplicationContext wac) {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
//    }

    @Test
    public void testPersonWithCars() throws Exception {
        Person person = new Person(
                (long)1,
                "Person 1",
                LocalDate.now()
        );
        personRepository.savePerson(person);

        Car car = new Car(
            (long)3,
            "Lada",
            "Kalina",
            100,
            person
        );
        carRepository.saveCar(car);

        mockMvc.perform(get("/personwithcars").param("personid", Long.toString(person.getId())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(person.getId()));
    }
}