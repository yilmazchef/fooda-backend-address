package be.fooda.backend.address;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@EnableEurekaClient
public class FoodaAddressApp {

    public static void main(String[] args) {
        SpringApplication.run(FoodaAddressApp.class, args);
    }

}
