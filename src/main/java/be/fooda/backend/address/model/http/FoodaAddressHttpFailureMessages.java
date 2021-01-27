package be.fooda.backend.address.model.http;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FoodaAddressHttpFailureMessages {
    USER_DOES_NOT_EXIST("There is no such user with the given information"),
    PROBLEM_CREATING_ADDRESS("could not create address"),
    PROBLEM_UPDATING_ADDRESS("could not update address db"),
     ADDRESS_ALREADY_EXISTS("address already exists") ,
     PROBLEM_FETCHING_THE_LIST_OF_ADDRESSES("list of addresses cant be fetched") ,
     ADDRESS_DOES_NOT_EXIST("Address doesn't exist") ,
    ADDRESS_COULD_NOT_BE_DELETED("could nor delete the address");
    private final String description;
}
