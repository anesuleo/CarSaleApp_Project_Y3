package ie.atu.carsaleapp_project_y3.feignclients;

import ie.atu.carsaleapp_project_y3.entity.Car;
import ie.atu.carsaleapp_project_y3.entity.Customer;
import ie.atu.carsaleapp_project_y3.entity.Store;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
//customer to access cars,//CarService communicating with customerService
@FeignClient(name="car-service", url ="http://localhost:8080")
public interface CustomerClient {

    @PostMapping("/addCustomer")
    public Customer addCustomer(@Valid @RequestBody Customer customer);

        @GetMapping("/cars/allCars")
    List<Car>getAllCars();

    @GetMapping("/cars/{id}")
      Car getCarById(@PathVariable Long id);

    @GetMapping("store/allStores")
    public List<Store> getAllStores();
}
