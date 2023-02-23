package co.incubyte.car;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;

@Client("https://myfakeapi.com/api/")
public interface CarClient {
    @Get("cars/{id}")
    CarWrapper fetch(String id);

    @Get("cars")
    CarsWrapper fetchAll();
}
