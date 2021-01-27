package be.fooda.backend.address.model.read;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FoodaAddressExample {

    private String door;

    private String house;

    private String street;

    private String title;

    private Boolean isCurrent;

    private FoodaAddressUserExample user;

    private FoodaAddressCoordinatesExample coordinates;

    private String municipality;

    private String postcode;

    private String city;

    private String region;

    private String country;

    private String countryCode;


}









