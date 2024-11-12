package ie.atu.carsaleapp_project_y3.controller;

import ie.atu.carsaleapp_project_y3.entity.Car;
import ie.atu.carsaleapp_project_y3.entity.Customer;
import ie.atu.carsaleapp_project_y3.entity.Store;
import ie.atu.carsaleapp_project_y3.feignclients.CustomerClient;
import ie.atu.carsaleapp_project_y3.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerClient customerClient;
    @Autowired
    public CustomerController(CustomerService customerService, CustomerClient carClient) {
        this.customerService = customerService;
        this.customerClient = carClient;
    }
    @PostMapping("/addCustomer")
    public Customer addCustomer(@Valid @RequestBody Customer customer){
        return customerService.addCustomer(customer);
    }

    @GetMapping("/allCustomers")
    public List<Customer> getAllCustomer(){
        return customerService.getAllCustomer();
    }

//fetch all cars from carservice using customer client

    @GetMapping("/allCars")
    public List<Car> getAllCars() {
        return customerClient.getAllCars();
    }


    //fetch all stores from store service using customer client
    @GetMapping("/allStores")
    public List<Store> getAllStores(){
        return customerClient.getAllStores();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Customer loginRequest) {
        Optional<Customer> customer = customerService.findByEmail(loginRequest.getEmail());

        if (customer.isPresent() && customer.get().getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.ok(Map.of("success", true));
        } else if (customer.isPresent()) {
            return ResponseEntity.ok(Map.of("success", false, "message", "Incorrect password."));
        } else {
            return ResponseEntity.status(404).body(Map.of("message", "No account found."));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@Valid @RequestBody Customer customer) {
        Customer newCustomer = customerService.addCustomer(customer);
        return ResponseEntity.ok(newCustomer);
    }

}
