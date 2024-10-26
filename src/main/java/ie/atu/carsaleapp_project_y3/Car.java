package ie.atu.carsaleapp_project_y3;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "car")
public class Car {
    @Id
    @Column(name = "car_id")
    private int car_id;
    @Column(name = "make")
    private String make;
    @Column(name = "model")
    private String model;
    @Column(name = "year")
    private int year;
    @Column(name = "cost")
    private float cost;

}
