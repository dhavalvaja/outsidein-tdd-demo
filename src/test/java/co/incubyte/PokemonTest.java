package co.incubyte;

import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@MicronautTest
public class PokemonTest {
    @Client("/")
    @Inject
    HttpClient httpClient;

    @Test
    @DisplayName("return pokemon by id")
    void return_pokemon_by_id() {
        Pokemon pokemon = httpClient.toBlocking().retrieve("pokemon/1", Pokemon.class);
        String name = pokemon.getName();
        Assertions.assertThat(name).isEqualTo("bulbasaur");
    }
}
