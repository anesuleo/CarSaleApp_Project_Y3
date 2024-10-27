package ie.atu.carsaleapp_project_y3.repository;

import ie.atu.carsaleapp_project_y3.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {

}

