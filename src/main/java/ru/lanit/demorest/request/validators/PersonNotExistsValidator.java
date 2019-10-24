package ru.lanit.demorest.request.validators;

import org.springframework.transaction.annotation.Transactional;
import ru.lanit.demorest.repository.interfaces.PersonRepositoryInterface;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PersonNotExistsValidator implements ConstraintValidator<PersonNotExists, Long> {

    private final PersonRepositoryInterface personRepository;

    public PersonNotExistsValidator(PersonRepositoryInterface personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    @Transactional
    public boolean isValid(Long personId, ConstraintValidatorContext constraintValidatorContext) {
        if (personId != null) {
            return personRepository.getById(personId) == null;
        } else {
            return false;
        }
    }
}