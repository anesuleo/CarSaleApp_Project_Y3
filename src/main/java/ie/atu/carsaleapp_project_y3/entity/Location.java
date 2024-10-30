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
@Entity
@Data
@Table(name = "Location")
public class  Location {
    @Id
    @Column(name = "location_id")
    private int location_id;
    @Column(name = "customer_id")
    private int customer_id;
    @Column(name = "county")
    private String county;
    @Column(name = "country")
    private String country;
    @Column(name = "city")
    private String city;
    @Column(name = "eircode")
    private String eircode;
}
