package be.fooda.backend.address.client;

import be.fooda.backend.address.model.create.FoodaAddressUserCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class FoodaUserClient {
    private final RestTemplate restClient;

    public boolean exists(FoodaAddressUserCreate user) {
        return true;
    }

    public boolean exists(Long externalUserId) {
        return true;
    }
}
