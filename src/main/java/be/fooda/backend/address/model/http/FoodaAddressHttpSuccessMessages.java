package be.fooda.backend.address.model.http;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FoodaAddressHttpSuccessMessages {
    ADDRESS_ADDED("Address is added"),
    ADDRESS_UPDATED("Address is updated"),
    ADDRESS_DELETED("address is deleted") ;
    private final String description;
}
