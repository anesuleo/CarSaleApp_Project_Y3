package ie.atu.carsaleapp_project_y3.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "Location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private int location_id;

    @Column(name = "county")
    @NotBlank(message = "county is not blank")
    private String county;

    @Column(name = "country")
    @NotBlank(message = "country is not blank")
    private String country;

    @Column(name = "city")
    @NotBlank(message = "city is not blank")
    private String city;

    @Column(name = "eircode")
    @NotBlank(message = "eircode is not blank")
    private String eircode;
}
