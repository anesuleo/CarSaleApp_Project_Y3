package ie.atu.carsaleapp_project_y3.controller;

import ie.atu.carsaleapp_project_y3.entity.Customer;
import ie.atu.carsaleapp_project_y3.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/customer")
public class CustomerController {
    private CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @PostMapping("/addCustomer")
    public Customer addCustomer(@Valid @RequestBody Customer customer){
        return customerService.addCustomer(customer);
    }

    @GetMapping("/allCustomers")
    public List<Customer> getAllCustomer(){
        return customerService.getAllCustomer();
    }



}
