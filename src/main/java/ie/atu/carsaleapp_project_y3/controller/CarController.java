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

//http://localhost:63342
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
    @PutMapping("/updatePassword/{email}")
    public ResponseEntity<String> updateCustomerPassword(@PathVariable String email, @RequestBody Map<String, String> passwordRequest) {
        return carClient.updatePassword(email, passwordRequest);
    }


    @DeleteMapping("/delete/{car_id}")
    public ResponseEntity<String> deleteCar(@PathVariable Long car_id) {
        String result = carService.deleteCarById(car_id);
        return ResponseEntity.ok(result); // Respond with the result message
    }

    @PutMapping("/updatePrice/{car_id}")
    public ResponseEntity<String> updatePrice(@PathVariable Long car_id, @RequestBody Map<String, Double> priceRequest) {
        Double newPrice = priceRequest.get("cost");

        if (newPrice == null || newPrice <= 0) {
            return ResponseEntity.badRequest().body("Invalid price provided");
        }

        boolean isUpdated = carService.updateCarPrice(car_id, newPrice);

        if (isUpdated) {
            return ResponseEntity.ok("Car price updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found.");
        }
    }


    //fetch stores from store service using carClient
//    @GetMapping("/allStores")
//    public List<Store> getAllStores(){
//        return carClient.getAllStores();
//    }
}
