package ie.atu.carsaleapp_project_y3.feignclients;

import ie.atu.carsaleapp_project_y3.entity.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//store  access cars and customers
@FeignClient(name ="store-service",url ="http://localhost:8080")
public interface StoreClient {
    @GetMapping("/cars/allCars")
    List<Car>getAllCars();

    @GetMapping("/cars/{id}")
    Car getCarById(@PathVariable Long id);

}
