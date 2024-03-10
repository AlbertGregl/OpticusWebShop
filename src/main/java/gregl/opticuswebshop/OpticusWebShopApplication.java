package gregl.opticuswebshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "gregl.opticuswebshop.controller",
    "gregl.opticuswebshop.configuration",
    "gregl.opticuswebshop.DTO",
    "gregl.opticuswebshop.service",})
public class OpticusWebShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpticusWebShopApplication.class, args);
    }

}
