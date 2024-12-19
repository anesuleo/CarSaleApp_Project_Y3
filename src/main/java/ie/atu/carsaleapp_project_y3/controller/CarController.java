package ie.atu.carsaleapp_project_y3.controller;

import ie.atu.carsaleapp_project_y3.entity.Car;
import ie.atu.carsaleapp_project_y3.entity.Customer;
import ie.atu.carsaleapp_project_y3.entity.Store;
import ie.atu.carsaleapp_project_y3.feignclients.CarClient;
import ie.atu.carsaleapp_project_y3.service.CarService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins ="http://localhost:63342" )
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
    public ResponseEntity<String> addCar(@Valid @RequestBody Car car) {
        carService.addCar(car);
        return new ResponseEntity<>("Car Created Successfully", HttpStatus.OK);
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
//accessing customer through feign
    @GetMapping("/allCustomers")
    public List<Customer> fetchAllCustomers() {
        return carClient.getAllCustomers();
    }
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginCustomer(@RequestBody Customer loginRequest) {
        return carClient.login(loginRequest);
    }
    @PostMapping("/add")
    public Customer addNewCustomer(@RequestBody Customer customer) {
        return carClient.addCustomer(customer);
    }
    @PostMapping("/register")
    public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer) {
        return carClient.registerCustomer(customer);
    }

    //fetch stores from store service using carClient
//    @GetMapping("/allStores")
//    public List<Store> getAllStores(){
//        return carClient.getAllStores();
//    }
}
