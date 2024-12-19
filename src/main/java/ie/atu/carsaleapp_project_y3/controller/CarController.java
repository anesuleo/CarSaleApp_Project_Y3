package ie.atu.carsaleapp_project_y3.controller;

import ie.atu.carsaleapp_project_y3.entity.Car;
import ie.atu.carsaleapp_project_y3.entity.Customer;
import ie.atu.carsaleapp_project_y3.entity.Store;
import ie.atu.carsaleapp_project_y3.feignclients.CarClient;
import ie.atu.carsaleapp_project_y3.service.CarService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins ="*" )
@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;
    private final CarClient carClient;

    public CarController(CarService myService, CarClient carClient){
        this.carService = myService;
        this.carClient = carClient;
    }

    @PostMapping("/addCar")
    public Car addCar(@Valid @RequestBody Car car) {
        return carService.addCar(car);
    }

    @GetMapping("/allcars")
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        Optional<Car> car = carService.getCarById(id);

        return car.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/allCustomers")
    public List<Customer> fetchAllCustomers() {
        return carClient.getAllCustomers();
    }
    @PostMapping("/customer/login")
    public ResponseEntity<Map<String, Object>> loginCustomer(@RequestBody Customer loginRequest) {
        return carClient.login(loginRequest);
    }
    @PostMapping("/customer/add")
    public Customer addNewCustomer(@RequestBody Customer customer) {
        return carClient.addCustomer(customer);
    }
    @PostMapping("/customer/register")
    public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer) {
        return carClient.registerCustomer(customer);
    }

    //fetch customers from customer service using carClient

    //fetch stores from store service using carClient
//    @GetMapping("/allStores")
//    public List<Store> getAllStores(){
//        return carClient.getAllStores();
//    }
}
