package ie.atu.carsaleapp_project_y3.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @Column(name = "info_id")
    private int info_id;
    @Column(name = "car_id")
    private int car_id;
    @Column(name = "colour")
    private String colour;
    @Column(name = "engine_size")
    private float engine_size;
    @Column(name = "horsepower")
    private int horsepower;
}
