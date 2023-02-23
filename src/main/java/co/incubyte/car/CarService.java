package co.incubyte.car;

import jakarta.inject.Singleton;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Singleton
public class CarService {
    private final CarClient carClient;

    public CarService(CarClient carClient) {
        this.carClient = carClient;
    }

    public CarWrapper getCar(String id) {
        return carClient.fetch(id);
    }

    public CarsWrapper getCars() {
        return carClient.fetchAll();
    }

    public List<Car> getCarsByYear(int year) {
        List<Car> fetchedCars = carClient.fetchAll().getCars();
        List<Car> cars = fetchedCars
                .stream()
                .filter(car -> car.getYear() == year)
                .collect(Collectors.toList());
        return cars;
    }

    public List<Car> getCarsByModel(String model) {
        List<Car> fechedCars = carClient.fetchAll().getCars();
        List<Car> cars = fechedCars.stream().filter(car -> Objects.equals(car.getModel(), model)).collect(Collectors.toList());
        return cars;
    }
}
