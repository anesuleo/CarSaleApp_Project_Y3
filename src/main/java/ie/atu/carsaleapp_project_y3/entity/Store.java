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
@Table(name = "store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private int store_id;

    @Column(name = "car_id")
    private int car_id;

    @Column(name = "customer_id")
    private int customer_id;

    @Column(name = "store_name")
    @NotBlank(message = "storeName not blank")
    private String storeName;

    @Column(name = "store_city")
    @NotBlank(message ="storeCity not blank")
    private String storeCity;

    @Column(name = "store_county")
    @NotBlank(message = "storeCounty not blank")
    private String storeCounty;
}
