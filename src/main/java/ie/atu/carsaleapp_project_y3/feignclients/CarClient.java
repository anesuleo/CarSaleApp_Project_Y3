package ie.atu.carsaleapp_project_y3.feignclients;

import ie.atu.carsaleapp_project_y3.entity.Customer;
import ie.atu.carsaleapp_project_y3.entity.Store;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//CarService communicating with customerService,cars to access customers,
@FeignClient(name = "customer-service", url="http://localhost:8082/customer")
public interface CarClient {
    @GetMapping("/allCustomers")
    List<Customer> getAllCustomers();
    @PostMapping("/login")
    ResponseEntity<Map<String, Object>> login(@RequestBody Customer loginRequest);
    @PostMapping("/addCustomer")
    Customer addCustomer(@RequestBody Customer customer);
    @PostMapping("/register")
    ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer);
    @PutMapping("/updatePassword/{email}")
    ResponseEntity<String> updatePassword(@PathVariable String email, @RequestBody Map<String, String> passwordRequest);

}
