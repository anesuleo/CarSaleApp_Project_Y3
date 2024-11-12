package ie.atu.carsaleapp_project_y3.repository;

import ie.atu.carsaleapp_project_y3.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
}
