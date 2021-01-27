package be.fooda.backend.address.model.update;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FoodaAddressUpdate {

    private String door;

    private String house;

    private String street;

    private String title;

    private Boolean isCurrent;

    private FoodaAddressUserUpdate user;

    private String municipality;

    private String postcode;

    private String city;

    private String region;

    private String country;

    private String countryCode;

    private FoodaAddressCoordinatesUpdate coordinates;

    }