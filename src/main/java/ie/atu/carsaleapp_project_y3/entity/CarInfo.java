package ie.atu.carsaleapp_project_y3.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Car_info")
public class CarInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "info_id")
    private int info_id;

    @Column(name = "colour")
    @NotBlank(message = "colour cannot be blank")
    private String colour;

    @Column(name = "engine_size")
    @NotNull(message = "engine_size cannot be 0")
    private float engine_size;

    @Column(name = "horsepower")
    @NotNull(message = "horsepower cannot be 0")
    private int horsepower;

    @OneToOne
    @JoinColumn(name = "car_id", referencedColumnName = "car_id")
    private Car car;
}
