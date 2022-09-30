package se.lexicon.mymappingstatusexercise.dao;

import se.lexicon.mymappingstatusexercise.model.Car;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CarDao {

    Car save(Car car);
    Optional<Car> findById(int carId);
    Collection<Car> findAll();
    void remove(int carId);
    Car update(Car car);
    Collection<Car> saveCars(List<Car> cars);
}
