package co.incubyte;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PokemonControllerShould {
    PokemonController pokemonController;
    @Mock
    PokemonService pokemonService;

    @Test
    @DisplayName("return pokemon by id")
    void return_pokemon_by_id() {
        int id = 1;
        pokemonController = new PokemonController(pokemonService);
        Pokemon expectedPokemon = new Pokemon("bulbasaur");
        Mockito.when(pokemonService.getById(id)).thenReturn(expectedPokemon);

        Pokemon pokemon = pokemonController.getById(id);
        String pokemonName = pokemon.getName();

        Mockito.verify(pokemonService).getById(id);
    }
}