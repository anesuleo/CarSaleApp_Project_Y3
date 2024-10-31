package ie.atu.carsaleapp_project_y3.controller;

import ie.atu.carsaleapp_project_y3.entity.Car;
import ie.atu.carsaleapp_project_y3.entity.Customer;
import ie.atu.carsaleapp_project_y3.feignclients.CarClient;
import ie.atu.carsaleapp_project_y3.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final CarClient carClient;
    @Autowired
    public CustomerController(CustomerService customerService, CarClient carClient) {
        this.customerService = customerService;
        this.carClient = carClient;
    }
    @PostMapping("/addCustomer")
    public Customer addCustomer(@Valid @RequestBody Customer customer){
        return customerService.addCustomer(customer);
    }

    @GetMapping("/allCustomers")
    public List<Customer> getAllCustomer(){
        return customerService.getAllCustomer();
    }

//fetch all cars from carservice using car client

    @GetMapping("/allCars")
    public List<Car> getAllCars() {
        return carClient.getAllCars();
    }

}
