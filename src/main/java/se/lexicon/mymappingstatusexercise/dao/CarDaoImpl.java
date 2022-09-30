package se.lexicon.mymappingstatusexercise.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.mymappingstatusexercise.model.Car;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public class CarDaoImpl implements CarDao{


    @PersistenceContext
    EntityManager entityManager;


    @Transactional(rollbackFor = IllegalArgumentException.class)
    @Override
    public Car save(Car car) {
        if(car == null) throw new IllegalArgumentException("Car not to be null");
        if(car.getCarId() <= 0){
            entityManager.persist(car);
            return car;
        }else {
            entityManager.merge(car);
        }
        return car;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Car> findById(int carId) {
        Car car = entityManager.find(Car.class, carId);
        return Optional.ofNullable(car);
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<Car> findAll() {
        return entityManager.createQuery("SELECT c FROM Car c", Car.class).getResultList();
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void remove(int carId) {
        findById(carId).ifPresent(entityManager::remove);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public Car update(Car car) {
        return entityManager.merge(car);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public Collection<Car> saveCars(List<Car> cars) {
        cars.forEach(this::save);
        return cars;
    }
}