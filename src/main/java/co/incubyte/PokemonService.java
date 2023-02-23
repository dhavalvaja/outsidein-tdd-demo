package co.incubyte;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class PokemonService {
    @Client("https://pokeapi.co/api/v2/")
    @Inject
    HttpClient httpClient;

    public Pokemon getById(int id) {
        MutableHttpRequest<Object> request = HttpRequest.GET("pokemon/" + id);
        return httpClient.toBlocking().retrieve(request, Pokemon.class);
    }
}
