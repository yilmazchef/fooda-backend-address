package be.fooda.backend.address.model.create;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FoodaAddressUserCreate {

    private Long externalUserId;

    private String username;


}
