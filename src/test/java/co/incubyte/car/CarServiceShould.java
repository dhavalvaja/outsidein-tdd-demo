package co.incubyte.car;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarServiceShould {
    @Mock
    CarClient carClient;
    CarService carService;

    @BeforeEach
    public void init() {
        carService = new CarService(carClient);
    }

    @Test
    @DisplayName("call CarClient")
    void call_car_client() {
        CarWrapper carWrapper = carService.getCar("1");
        verify(carClient).fetch("1");
    }

    @Test
    @DisplayName("call CarClient to fetch all the cars")
    void call_car_client_to_fetch_all_the_cars() {
        CarsWrapper carsWrapper = carService.getCars();
        verify(carClient).fetchAll();
    }


    @Test
    @DisplayName("return cars have year specified")
    void return_cars_have_year_specified() {
        Car car1 = new Car("1", "Mitsubishi", 2002, "model");
        Car car2 = new Car("2", "Volkswagen", 2008, "model");
        Car car3 = new Car("3", "Saturn", 2003, "model");
        Car car4 = new Car("9", "BMW", 2008, "model");
        when(carClient.fetchAll()).thenReturn(new CarsWrapper(List.of(car1, car2, car3, car4)));

        List<Car> cars = carService.getCarsByYear(2008);

        assertThat(cars).allSatisfy(car -> assertThat(car.getYear()).isEqualTo(2008));
    }

    @Test
    @DisplayName("return cars have model specified")
    void return_cars_have_model_specified() {
        Car car1 = new Car("1", "Mitsubishi", 2002, "Montero");
        Car car2 = new Car("2", "Volkswagen", 2008, "Passat");
        Car car3 = new Car("3", "Saturn", 2003, "L-Series");
        Car car4 = new Car("46", "Mitsubishi", 1996, "Montero");
        when(carClient.fetchAll()).thenReturn(new CarsWrapper(List.of(car1, car2, car3, car4)));

        List<Car> cars = carService.getCarsByModel("Montero");
        assertThat(cars.size()).isEqualTo(2);
        assertThat(cars).allSatisfy(car -> assertThat(car.getModel()).isEqualTo("Montero"));
    }
}