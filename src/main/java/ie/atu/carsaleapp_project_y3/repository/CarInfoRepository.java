package ie.atu.carsaleapp_project_y3.repository;

import ie.atu.carsaleapp_project_y3.entity.CarInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarInfoRepository extends JpaRepository<CarInfo, Long> {
}
