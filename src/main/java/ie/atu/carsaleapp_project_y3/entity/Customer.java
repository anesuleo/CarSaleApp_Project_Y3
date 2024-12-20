package ie.atu.carsaleapp_project_y3.entity;

import lombok.Data;

@Data
public class Customer {
    private int customer_id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNo;
    private String password;
}
