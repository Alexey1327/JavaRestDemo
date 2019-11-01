package ru.lanit.demorest.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.lanit.demorest.entity.Person;
import ru.lanit.demorest.repository.interfaces.PersonRepositoryInterface;

import java.math.BigInteger;

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
    @Transactional
    public void savePerson(Person person) {
        getSession().save(person);
    }

    @Override
    @Transactional
    public Person getById(Long peopleId) {
        return getSession().get(Person.class, peopleId);
    }

    @Override
    @Transactional
    public Long countAll() {
        BigInteger result = (BigInteger) getSession().createNativeQuery("SELECT COUNT(1) AS cnt FROM person").getSingleResult();

        return result.longValue();
    }

    @Override
    @Transactional
    public void deleteAll() {
        getSession().createNativeQuery("DELETE from person").executeUpdate();
    }
}