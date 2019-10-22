package ru.lanit.demorest.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.lanit.demorest.entity.Car;
import ru.lanit.demorest.repository.interfaces.CarRepositoryInterface;

@Component
public class CarRepository implements CarRepositoryInterface {

    private SessionFactory sessionFactory;

    @Autowired
    public CarRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    @Transactional
    public void saveCar(Car car) {
        getSession().save(car);
    }
}