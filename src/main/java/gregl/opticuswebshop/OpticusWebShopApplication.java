package gregl.opticuswebshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "gregl.opticuswebshop.BL",
    "gregl.opticuswebshop.DAL",
    "gregl.opticuswebshop.algebralab",
    "gregl.opticuswebshop.MVC",})
public class OpticusWebShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpticusWebShopApplication.class, args);
    }

}
