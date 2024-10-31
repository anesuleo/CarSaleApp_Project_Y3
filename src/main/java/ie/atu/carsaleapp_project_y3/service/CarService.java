package ie.atu.carsaleapp_project_y3.service;

import ie.atu.carsaleapp_project_y3.entity.Car;
import ie.atu.carsaleapp_project_y3.entity.Customer;
import ie.atu.carsaleapp_project_y3.feignclients.CarClient;
import ie.atu.carsaleapp_project_y3.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final CarClient carClient;

    public CarService(CarRepository carRepository, CarClient carClient){
        this.carRepository = carRepository;
        this.carClient = carClient;
    }
    public List<Customer> getAllCustomersFromCustomerService() {
        return carClient.getAllCustomer();
    }

    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    public List<Car> getAllCars(){
        return carRepository.findAll();
    }

    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

}
