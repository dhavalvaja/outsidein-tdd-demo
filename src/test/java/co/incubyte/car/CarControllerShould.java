package co.incubyte.car;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CarControllerShould {
    @Mock
    CarService carService;
    CarController carController;

    @BeforeEach
    public void init() {
        carController = new CarController(carService);
    }

    @Test
    @DisplayName("call CarService")
    void call_car_service() {
        CarWrapper carWrapper = carController.getCar("1");
        verify(carService).getCar("1");
    }

    @Test
    @DisplayName("call CarService to get all cars")
    void call_car_service_to_get_all_cars() {
        CarsWrapper carsWrapper = carController.getCars();
        verify(carService).getCars();
    }

    @Test
    @DisplayName("call CarService to get cars by year")
    void call_car_service_to_get_cars_by_year() {
        List<Car> carsWrapper = carController.getCarsByYear(2008);
        verify(carService).getCarsByYear(2008);
    }

    @Test
    @DisplayName("call CarService to get cars by model")
    void call_car_service_to_get_cars_by_model() {
        List<Car> cars = carController.getCarsByModel("Montero");
        verify(carService).getCarsByModel("Montero");
    }
}
