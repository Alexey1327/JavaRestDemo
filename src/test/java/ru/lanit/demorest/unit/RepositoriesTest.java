package ru.lanit.demorest.unit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.lanit.demorest.config.AppConfig;
import ru.lanit.demorest.entity.Car;
import ru.lanit.demorest.entity.Person;
import ru.lanit.demorest.repository.interfaces.CarRepositoryInterface;
import ru.lanit.demorest.repository.interfaces.PersonRepositoryInterface;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= {AppConfig.class})
@WebAppConfiguration
public class RepositoriesTest {

    @Autowired
    private PersonRepositoryInterface personRepository;

    @Autowired
    private CarRepositoryInterface carRepository;

    @Test
    @Transactional
    public void testPersonRepository() {
        Person person1 = new Person((long)1, "Person 1", LocalDate.now());

        personRepository.savePerson(person1);

        assertEquals(personRepository.getById(person1.getId()), person1);
    }

    @Test
    @Transactional
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

    @Test
    public void testStatistics() {

        Person person1 = new Person((long)1, "Person 1", LocalDate.now());
        personRepository.savePerson(person1);

        Person person2 = new Person((long)2, "Person 2", LocalDate.now());
        personRepository.savePerson(person2);

        Car car1 = new Car(
                (long)3,
                "Lada",
                "Kalina",
                100,
                person1
        );
        carRepository.saveCar(car1);

        Car car2 = new Car(
                (long)4,
                "Lada",
                "Granta",
                100,
                person2
        );
        carRepository.saveCar(car2);

        assertEquals(personRepository.countAll(), Long.valueOf(2));
        assertEquals(carRepository.countAll(), Long.valueOf(2));
        assertEquals(carRepository.uniqueVendorCountAll(), Long.valueOf(1));
    }
}
