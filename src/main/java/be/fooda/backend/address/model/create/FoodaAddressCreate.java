package be.fooda.backend.address.model.create;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class FoodaAddressCreate {


    private String door;

    private String house;

    private String street;

    private Boolean isActive;

    private String title;

    private Boolean isCurrent;

    private LocalDateTime registryTime;

    private LocalDateTime updateTime;

    private FoodaAddressUserCreate user;

    private FoodaAddressCoordinatesCreate coordinates;

    private String municipality;

    private String postcode;

    private String city;

    private String region;

    private String country;

    private String countryCode;




}
