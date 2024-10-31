package ie.atu.carsaleapp_project_y3.controller;

import ie.atu.carsaleapp_project_y3.entity.Car;
import ie.atu.carsaleapp_project_y3.entity.Customer;
import ie.atu.carsaleapp_project_y3.entity.Store;
import ie.atu.carsaleapp_project_y3.feignclients.CarClient;
import ie.atu.carsaleapp_project_y3.service.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins ="http://localhost:63342" )
@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;
    private final CarClient carClient;
    @Autowired
    public CarController(CarService carService, CarClient customerClient){
        this.carService =carService;

        this.carClient =customerClient;
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
    //fetch all customers from customer service using car client
    @GetMapping("/allCustomers")
    public List<Customer> getAllCustomersFromCustomerService(){
        return carClient.getAllCustomer();
    }
//fetch all stores from store service using car client
@GetMapping("/allStores")
public List<Store> getAllStores(){
    return carClient.getAllStores();
}
}
