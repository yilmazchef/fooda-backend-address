package be.fooda.backend.address.model.update;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FoodaAddressUserUpdate {

    private Long externalUserId;

    private String username;


}
