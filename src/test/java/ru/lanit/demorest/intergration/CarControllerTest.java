package ru.lanit.demorest.intergration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import ru.lanit.demorest.config.AppConfig;
import ru.lanit.demorest.entity.Person;
import ru.lanit.demorest.repository.interfaces.CarRepositoryInterface;
import ru.lanit.demorest.repository.interfaces.PersonRepositoryInterface;
import ru.lanit.demorest.request.CarSaveRequest;
import ru.lanit.demorest.request.PersonSaveRequest;
import ru.lanit.demorest.request.validators.DateValidator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= {AppConfig.class})
@WebAppConfiguration
public class CarControllerTest {

    @Autowired
    private PersonRepositoryInterface personRepository;

    @Autowired
    private CarRepositoryInterface carRepository;

    @Autowired
    WebApplicationContext wac;

    ObjectMapper objectMapper;

    MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .build();
        this.objectMapper = Jackson2ObjectMapperBuilder.json().build();
    }

    @Test
    @Transactional
    public void testCarSaveSuccess() throws Exception {

        Person person = personRepository.getById((long)1);

        if (person == null) {
            person = new Person((long)1, "Person 1", LocalDate.now().minusYears(20));
            personRepository.savePerson(person);
        }

        CarSaveRequest request = new CarSaveRequest()
                .setId(1)
                .setModel("Lada-Granta")
                .setHorsepower(100)
                .setOwnerId(1);

        ResultActions resultActions = mockMvc.perform(
                post("/car")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        resultActions.andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void testCarSaveFail() throws Exception {

        Person person = personRepository.getById((long)1);

        if (person == null) {
            person = new Person((long)1, "Person 1", LocalDate.now());
            personRepository.savePerson(person);
        }

        CarSaveRequest request = new CarSaveRequest()
                .setId(1)
                .setModel("Lada")
                .setHorsepower(100)
                .setOwnerId(1);

        ResultActions resultActions = mockMvc.perform(
                post("/car")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        resultActions.andExpect(status().isBadRequest());
    }
}