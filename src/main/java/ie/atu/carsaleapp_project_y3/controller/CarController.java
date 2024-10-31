package ie.atu.carsaleapp_project_y3.controller;

import ie.atu.carsaleapp_project_y3.entity.Car;
import ie.atu.carsaleapp_project_y3.entity.Customer;
import ie.atu.carsaleapp_project_y3.feignclients.CustomerClient;
import ie.atu.carsaleapp_project_y3.service.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;
    private final CustomerClient customerClient;
    @Autowired
    public CarController(CarService carService,CustomerClient customerClient){
        this.carService =carService;
        this.customerClient =customerClient;
    }



    @PostMapping("/addCar")
    public Car addCar(@Valid @RequestBody Car car) {
        return carService.addCar(car);
    }

    @GetMapping("/allCars")
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        Optional<Car> car = carService.getCarById(id);

        return car.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    //fetch all customers from customer service
    @GetMapping("/allCustomers")
    public List<Customer> getAllCustomersFromCustomerService(){
        return customerClient.getAllCustomer();
    }

}
