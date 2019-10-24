package ru.lanit.demorest.repository.interfaces;

import ru.lanit.demorest.entity.Person;

public interface PersonRepositoryInterface {

    void savePerson(Person person);

    Person getById(Long peopleId);

    Long countAll();

    void deleteAll();
}
