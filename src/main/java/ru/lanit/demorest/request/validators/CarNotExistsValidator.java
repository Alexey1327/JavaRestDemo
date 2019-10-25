package ru.lanit.demorest.request.validators;

import org.springframework.transaction.annotation.Transactional;
import ru.lanit.demorest.repository.interfaces.CarRepositoryInterface;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CarNotExistsValidator implements ConstraintValidator<CarNotExists, Long> {

    private final CarRepositoryInterface carRepository;

    public CarNotExistsValidator(CarRepositoryInterface carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    @Transactional
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        if (id != null && id != 0) {
            return carRepository.getById(id) == null;
        } else {
            return false;
        }
    }
}
