package ru.lanit.demorest.intergration;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import ru.lanit.demorest.entity.Car;
import ru.lanit.demorest.entity.Person;
import ru.lanit.demorest.repository.interfaces.CarRepositoryInterface;
import ru.lanit.demorest.repository.interfaces.PersonRepositoryInterface;
import ru.lanit.demorest.request.CarSaveRequest;
import ru.lanit.demorest.request.PersonSaveRequest;
import ru.lanit.demorest.request.validators.DateValidator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= {AppConfig.class})
@WebAppConfiguration
public class IntergarationTests {

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
    public void testPersonWithCarsAction() throws Exception {

        long personId = 2;
        long carId = 2;

        PersonSaveRequest request = new PersonSaveRequest()
                .setId(personId)
                .setName("Person 2")
                .setBirthdate(LocalDate.now().minusYears(20).format(DateTimeFormatter.ofPattern(DateValidator.EUROPEAN_DATE_PATTERN)));

        ResultActions resultActions1 = mockMvc.perform(
                post("/person")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        resultActions1.andExpect(status().isOk());

        CarSaveRequest carSaveRequest = new CarSaveRequest()
                .setId(carId)
                .setModel("Lada-Granta")
                .setHorsepower(100)
                .setOwnerId(personId);

        ResultActions resultActions2 = mockMvc.perform(
                post("/car")
                        .content(objectMapper.writeValueAsString(carSaveRequest))
                        .contentType(MediaType.APPLICATION_JSON)
        );
        resultActions2.andExpect(status().isOk());

        mockMvc.perform(get("/personwithcars").param("personid", String.valueOf(personId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value((int) personId));
    }

    @Test
    public void testPersonSaveSuccess() throws Exception {

        long personId = 1;

        PersonSaveRequest request = new PersonSaveRequest()
                .setId(personId)
                .setName("Person 1")
                .setBirthdate(LocalDate.now().minusYears(20).format(DateTimeFormatter.ofPattern(DateValidator.EUROPEAN_DATE_PATTERN)));

        ResultActions resultActions = mockMvc.perform(
                post("/person")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        resultActions.andExpect(status().isOk());

        mockMvc.perform(get("/personwithcars").param("personid", String.valueOf(personId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(personId));
    }

    @Test
    public void testPersonSaveFail() throws Exception {

        PersonSaveRequest request = new PersonSaveRequest()
                .setId((long) 1)
                .setName(null)
                .setBirthdate(null);

        ResultActions resultActions = mockMvc.perform(
                post("/person")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        resultActions.andExpect(status().isBadRequest());
    }
}