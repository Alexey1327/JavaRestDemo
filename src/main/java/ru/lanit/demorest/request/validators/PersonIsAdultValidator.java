package ru.lanit.demorest.request.validators;

import org.springframework.transaction.annotation.Transactional;
import ru.lanit.demorest.entity.Person;
import ru.lanit.demorest.repository.interfaces.PersonRepositoryInterface;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;

public class PersonIsAdultValidator implements ConstraintValidator<PersonIsAdult, Long> {

    private static final int ADULT_AGE = 18;

    private final PersonRepositoryInterface personRepository;

    public PersonIsAdultValidator(PersonRepositoryInterface personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    @Transactional
    public boolean isValid(Long personId, ConstraintValidatorContext constraintValidatorContext) {
        if (personId != null) {
            Person person = personRepository.getById(personId);

            if (person == null) {
                return false;
            }

            return Period.between(person.getBirthDate(), LocalDate.now()).getYears() >= ADULT_AGE;
        } else {
            return false;
        }
    }
}
