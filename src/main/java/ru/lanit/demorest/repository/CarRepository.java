package ru.lanit.demorest.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.lanit.demorest.entity.Car;
import ru.lanit.demorest.repository.interfaces.CarRepositoryInterface;

import java.math.BigInteger;

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

    @Override
    public Car getById(Long carId) {
        return getSession().get(Car.class, carId);
    }

    @Override
    public Long countAll() {
        BigInteger result = (BigInteger) getSession().createNativeQuery("SELECT COUNT(1) AS cnt FROM car").getSingleResult();

        return result.longValue();
    }

    @Override
    public Long uniqueVendorCountAll() {
        BigInteger result = (BigInteger) getSession().createNativeQuery("SELECT COUNT(1) FROM (SELECT DISTINCT vendor from car) t").getSingleResult();

        return result.longValue();
    }
}