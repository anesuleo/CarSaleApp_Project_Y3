package ie.atu.carsaleapp_project_y3;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "store")
public class Store {
    @Id
    @Column(name = "store_id")
    private int store_id;
    @Column(name = "car_id")
    private int car_id;
    @Column(name = "customer_id")
    private int customer_id;
    @Column(name = "store_name")
    private String storeName;
    @Column(name = "store_city")
    private String storeCity;
    @Column(name = "store_county")
    private String storeCounty;
}
