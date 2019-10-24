package ru.lanit.demorest.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.lanit.demorest.entity.Person;
import ru.lanit.demorest.repository.interfaces.PersonRepositoryInterface;

@Component
public class PersonRepository implements PersonRepositoryInterface {

    private SessionFactory sessionFactory;

    @Autowired
    public PersonRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public void savePerson(Person person) {
        getSession().save(person);
    }

    @Override
    public Person getById(Long peopleId) {
        return getSession().get(Person.class, peopleId);
    }
}
