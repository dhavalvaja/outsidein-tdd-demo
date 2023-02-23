package co.incubyte.car;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.List;

@Controller
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @Get("/cars/{id}")
    public CarWrapper getCar(String id) {
        return carService.getCar(id);
    }

    @Get("/cars")
    public CarsWrapper getCars() {
        return carService.getCars();
    }

    @Get("/cars/year/{year}")
    public List<Car> getCarsByYear(int year) {
        return carService.getCarsByYear(year);
    }

    @Get("/cars/model/{model}")
    public List<Car> getCarsByModel(String model) {
       return carService.getCarsByModel(model);
    }
}
