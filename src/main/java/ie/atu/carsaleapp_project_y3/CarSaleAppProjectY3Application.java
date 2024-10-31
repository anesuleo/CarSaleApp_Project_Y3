package ie.atu.carsaleapp_project_y3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CarSaleAppProjectY3Application {

    public static void main(String[] args) {
        SpringApplication.run(CarSaleAppProjectY3Application.class, args);
    }

}
