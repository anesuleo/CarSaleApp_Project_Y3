package ie.atu.carsaleapp_project_y3.repository;

import ie.atu.carsaleapp_project_y3.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
