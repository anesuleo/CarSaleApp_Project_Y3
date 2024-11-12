package ie.atu.carsaleapp_project_y3.service;

import ie.atu.carsaleapp_project_y3.entity.Customer;
import ie.atu.carsaleapp_project_y3.feignclients.CustomerClient;
import ie.atu.carsaleapp_project_y3.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerClient carClient;

    public CustomerService(CustomerRepository customerRepository, CustomerClient carClient) {
        this.customerRepository = customerRepository;
        this.carClient = carClient;
    }

    public Customer addCustomer(Customer customer){
        Optional<Customer> existingCustomer = customerRepository.findByEmail(customer.getEmail());
        if (existingCustomer.isPresent()) {
            throw new IllegalArgumentException("A customer with this email already exists.");
        }

        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }


    // Method to find a customer by email for login
    public Optional<Customer> findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

}
