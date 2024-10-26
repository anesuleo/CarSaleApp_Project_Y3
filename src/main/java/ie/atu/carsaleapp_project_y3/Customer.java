package ie.atu.carsaleapp_project_y3;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Customer")
public class Customer {
    @Id
    @Column(name = "customer_id")
    private int customer_id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone_number")
    private int phoneNo;
    @Column(name = "email")
    private String email;

}
