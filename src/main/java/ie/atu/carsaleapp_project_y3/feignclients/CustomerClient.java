package ie.atu.carsaleapp_project_y3.feignclients;

import ie.atu.carsaleapp_project_y3.entity.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//CarService communicating with customerService
@FeignClient(name = "customer-service", url="http://localhost:8080")
public interface CustomerClient {
    @GetMapping("/customer/allCustomers")
    List<Customer> getAllCustomer();

    @GetMapping("/customer/{id}")
    Customer getCustomerById(@PathVariable Long id);

}
