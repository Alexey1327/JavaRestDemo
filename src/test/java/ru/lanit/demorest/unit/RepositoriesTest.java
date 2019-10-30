package ru.lanit.demorest.unit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import ru.lanit.demorest.config.AppConfig;
import ru.lanit.demorest.config.AppInit;
import ru.lanit.demorest.config.WebConfig;
import ru.lanit.demorest.entity.Car;
import ru.lanit.demorest.entity.Person;
import ru.lanit.demorest.repository.interfaces.CarRepositoryInterface;
import ru.lanit.demorest.repository.interfaces.PersonRepositoryInterface;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= AppConfig.class)
public class RepositoriesTest {

    @Autowired
    private PersonRepositoryInterface personRepository;

    @Autowired
    private CarRepositoryInterface carRepository;

    @Test
    public void testPersonRepository() {
        Person person1 = new Person((long)1, "Person 1", LocalDate.now());

        personRepository.savePerson(person1);

        assertEquals(personRepository.getById(person1.getId()), person1);
    }

    @Test
    public void testCarRepository() {

        Person person = personRepository.getById((long)1);

        if (person == null) {
            person = new Person((long)1, "Person 1", LocalDate.now());
            personRepository.savePerson(person);
        }

        Car car = new Car(
                (long)1,
                "Lada",
                "Kalina",
                100,
                person
        );
        carRepository.saveCar(car);
        assertEquals(carRepository.getById(car.getId()), car);
    }
}
