package ie.atu.carsaleapp_project_y3.service;

import ie.atu.carsaleapp_project_y3.entity.Customer;
import ie.atu.carsaleapp_project_y3.feignclients.CarClient;
import ie.atu.carsaleapp_project_y3.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CarClient carClient;

    public CustomerService(CustomerRepository customerRepository, CarClient carClient) {
        this.customerRepository = customerRepository;
        this.carClient = carClient;
    }

    public Customer addCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }

    public Optional<Customer> getCarById(Long id) {
        return customerRepository.findById(id);
    }

}
