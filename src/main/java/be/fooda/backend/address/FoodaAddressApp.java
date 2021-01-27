package be.fooda.backend.address;

import be.fooda.backend.address.config.LuceneIndexConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableEurekaClient
public class FoodaAddressApp {

    public static void main(String[] args) {
        SpringApplication.run(FoodaAddressApp.class, args);
    }

}
