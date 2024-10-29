package ie.atu.carsaleapp_project_y3.entity;

import jakarta.persistence.*;
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
    @NotBlank(message="This field can not be blank")
    private String make;

    @Column(name = "model")
    @NotBlank(message="This field can not be blank")
    private String model;

    @Column(name = "year")
    @NotNull(message="This value can not be 0")
    private int year;

    @Column(name = "cost")
    @NotNull(message="This value can not be 0")
    private float cost;

    @OneToOne(cascade = CascadeType.ALL)
    private CarInfo carInfo;

}
