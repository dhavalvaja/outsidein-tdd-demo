package co.incubyte.car;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Car {
    private final String id;
    private final String carMake;
    private final int year;
    private String model;

    public Car(
            @JsonProperty("id") String id,
            @JsonProperty("car") String carMake,
            @JsonProperty("car_model_year") int year,
            @JsonProperty("car_model") String model
    ) {
        this.id = id;
        this.carMake = carMake;
        this.year = year;
        this.model = model;
    }

    public String getId() {
        return id;
    }

    public String getCarMake() {
        return carMake;
    }

    public int getYear() {
        return year;
    }

    public String getModel() {
        return model;
    }
}
