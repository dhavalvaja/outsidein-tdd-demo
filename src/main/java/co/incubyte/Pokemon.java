package co.incubyte;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pokemon {
    private final String name;

    public Pokemon(@JsonProperty("name") String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
