package ie.atu.carsaleapp_project_y3.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private int car_id;

    @Column(name = "make")
    @NotBlank(message="make is not be blank")
    private String make;

    @Column(name = "model")
    @NotBlank(message="model is not be blank")
    private String model;

    @Column(name = "year")
    @Min(value = 1950,message = "car is too old")
    @Max(value =2025,message = "invalid year")
    private int year;

    @Column(name = "cost")
    @NotNull(message="cost cannot be 0")
    private float cost;

    @OneToOne(cascade = CascadeType.ALL)
    private CarInfo carInfo;

}
