package ie.atu.carsaleapp_project_y3.feignclients;

import ie.atu.carsaleapp_project_y3.entity.Car;
import ie.atu.carsaleapp_project_y3.entity.Store;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
//customer to access cars,//CarService communicating with customerService
@FeignClient(name="car-service", url ="http://localhost:8080")
public interface CustomerClient {
    @GetMapping("/cars/allCars")
    List<Car>getAllCars();

    @GetMapping("/cars/{id}")
      Car getCarById(@PathVariable Long id);

    @GetMapping("store/allStores")
    public List<Store> getAllStores();
}
