package ie.atu.carsaleapp_project_y3.service;

import ie.atu.carsaleapp_project_y3.entity.Store;
import ie.atu.carsaleapp_project_y3.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreService {
    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository){
        this.storeRepository =storeRepository;
    }

    public Store addStore(Store store){
        return storeRepository.save(store);
    }

    public List<Store> getAllStores(){
        return storeRepository.findAll();
    }

    public Optional<Store> getStoreById(Long id){
        return getStoreById(id);
    }
}
