package be.fooda.backend.address.model.read;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FoodaAddressUserExample {

    private Long externalUserId;

    private String username;


}
