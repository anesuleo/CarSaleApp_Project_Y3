package ie.atu.carsaleapp_project_y3.controller;

import ie.atu.carsaleapp_project_y3.entity.Store;
import ie.atu.carsaleapp_project_y3.service.StoreService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/store")
public class StoreController {
    private StoreService storeService;

    public StoreController(StoreService myService){
        this.storeService = myService;
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

}