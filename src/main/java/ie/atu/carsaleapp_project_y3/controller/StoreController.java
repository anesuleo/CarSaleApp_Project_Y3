package ie.atu.carsaleapp_project_y3.controller;

import ie.atu.carsaleapp_project_y3.entity.Car;
import ie.atu.carsaleapp_project_y3.entity.Customer;
import ie.atu.carsaleapp_project_y3.entity.Store;
import ie.atu.carsaleapp_project_y3.feignclients.StoreClient;
import ie.atu.carsaleapp_project_y3.service.StoreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/store")
public class StoreController {
    private final StoreService storeService;
    private final StoreClient storeClient;
    @Autowired
    public StoreController(StoreService myService, StoreClient storeClient){
        this.storeService = myService;
        this.storeClient = storeClient;
    }

    @PostMapping("/addStore")
    public Store addStore(@Valid @RequestBody Store store){
        return storeService.addStore(store);
    }

    @GetMapping("/allstores")
    public List<Store> getAllStores(){
        return storeService.getAllStores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Store> getStoreById(@PathVariable Long id) {
        Optional<Store> store = storeService.getStoreById(id);

        return store.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //fetch all cars from carservice using storeclient
    @GetMapping("/allCars")
    public List<Car> getAllCars() {
        return storeClient.getAllCars();
    }
    //fetch all customers from customer service using store client
    @GetMapping("/allCustomers")
    public List<Customer> getAllCustomersFromCustomerService(){
        return storeClient.getAllCustomer();
    }
}
