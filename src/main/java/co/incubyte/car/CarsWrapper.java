package co.incubyte.car;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CarsWrapper {
    private final List<Car> cars;

    public CarsWrapper(@JsonProperty("cars") List<Car> cars) {
        this.cars = cars;
    }

    public void addCars(Car...car) {
        cars.addAll(List.of(car));
    }

    public List<Car> getCars() {
        return cars;
    }
}
