package co.incubyte.car;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
public class CarControllerTest {
    @Inject
    @Client("/")
    HttpClient httpClient;

    @Test
    @DisplayName("should return car by id")
    void should_return_car_by_id() {
        CarWrapper carWrapper = httpClient.toBlocking().retrieve("/cars/1", CarWrapper.class);
        Car car = carWrapper.getCar();
        assertThat(car.getId()).isEqualTo("1");
        assertThat(car.getCarMake()).isEqualTo("Mitsubishi");
    }

    @Test
    @DisplayName("should return list of cars")
    void should_return_list_of_cars() {
        CarsWrapper carsWrapper = httpClient.toBlocking().retrieve("/cars", CarsWrapper.class);
        List<Car> cars = carsWrapper.getCars();

        assertThat(cars.get(0).getId()).isEqualTo("1");
        assertThat(cars.get(1).getCarMake()).isEqualTo("Volkswagen");
        assertThat(cars.get(13).getCarMake()).isEqualTo("Volvo");
        assertThat(cars.size()).isEqualTo(1000);
    }

    @Test
    @DisplayName("should return car for given year")
    void should_return_car_for_given_year() {
        List<Car> cars = httpClient.toBlocking().retrieve(HttpRequest.GET("/cars/year/2008"), Argument.listOf(Car.class));
        assertThat(cars.size()).isEqualTo(48);
        assertThat(cars).allSatisfy(car -> assertThat(car.getYear()).isEqualTo(2008));
    }
    

    @Test
    @DisplayName("should return car fot given model")
    void should_return_car_fot_given_model() {
        List<Car> cars = httpClient.toBlocking().retrieve(HttpRequest.GET("/cars/model/Quest"),Argument.listOf(Car.class));
        assertThat(cars.size()).isEqualTo(2);
        assertThat(cars).allSatisfy(car -> assertThat(car.getModel()).isEqualTo("Quest"));
    }
}
