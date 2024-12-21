package ie.atu.carsaleapp_project_y3.service;

import ie.atu.carsaleapp_project_y3.entity.Car;
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
//    public List<Customer> getAllCustomersFromCustomerService() {
//        return carClient.getAllCustomer();
//    }

    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    public List<Car> getAllCars(){
        return carRepository.findAll();
    }

    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    public String deleteCarById(Long car_id) {
        Optional<Car> car = carRepository.findById(car_id); // Fetch the car by ID

        if (car.isPresent()) {
            carRepository.delete(car.get()); // Delete the found car entity
            return "Car deleted successfully.";
        } else {
            return "Car not found.";
        }
    }

    public boolean updateCarPrice(Long car_id, Double newPrice) {
        Optional<Car> carOptional = carRepository.findById(car_id);

        if (carOptional.isPresent()) {
            Car car = carOptional.get();
            car.setCost(newPrice); // Update the price
            carRepository.save(car); // Save the updated car back to the database
            return true;
        } else {
            return false; // Car not found
        }
    }
}
