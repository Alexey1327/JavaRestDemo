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

    @Test
    public void testPersonWithCarsAction() throws Exception {

        long personId = 1;
        long carId = 1;

        mockMvc.perform(
                get("/clear")
        ).andExpect(status().isOk());

        PersonSaveRequest request = new PersonSaveRequest()
                .setId(personId)
                .setName("Person 2")
                .setBirthdate(LocalDate.now().minusYears(20).format(DateTimeFormatter.ofPattern(DateValidator.EUROPEAN_DATE_PATTERN)));

        mockMvc.perform(
                post("/person")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        CarSaveRequest carSaveRequest = new CarSaveRequest()
                .setId(carId)
                .setModel("Lada-Granta")
                .setHorsepower(100)
                .setOwnerId(personId);

        mockMvc.perform(
                post("/car")
                        .content(objectMapper.writeValueAsString(carSaveRequest))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        ResultActions resultActions = mockMvc.perform(get("/personwithcars").param("personid", String.valueOf(personId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value((int) personId));

        mockMvc.perform(get("/statistics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.personcount").value(1))
                .andExpect(jsonPath("$.carcount").value(1))
                .andExpect(jsonPath("$.uniquevendorcount").value(1));
    }
}