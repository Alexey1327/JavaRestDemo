package ru.lanit.demorest.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lanit.demorest.entity.Person;

public interface PersonRepositoryInterface extends JpaRepository<Person, Long> {

}
