package pl.kaczorowski.carrent.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String vehicleIdentifier;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private VehicleCategory category;

    public Vehicle(String name, String vehicleIdentifier, VehicleCategory category) {
        this.name = name;
        this.vehicleIdentifier = vehicleIdentifier;
        this.category = category;
    }
}
