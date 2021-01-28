package be.fooda.backend.address;

import be.fooda.backend.address.config.LuceneIndexConfig;
import be.fooda.backend.address.config.RestClientConfig;
import be.fooda.backend.address.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableAutoConfiguration
@EnableEurekaClient
@EnableSwagger2
@Import({
        LuceneIndexConfig.class, RestClientConfig.class, SwaggerConfig.class
})
public class FoodaAddressApp {

    public static void main(String[] args) {
        SpringApplication.run(FoodaAddressApp.class, args);
    }

}
