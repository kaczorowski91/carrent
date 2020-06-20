package pl.kaczorowski.carrent.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private double costPerDay;
    @OneToMany(mappedBy = "vehicle")
    private List<Assignment>assignments=new ArrayList<>();


    public Vehicle(String name, String vehicleIdentifier, VehicleCategory category, double costPerDay) {
        this.name = name;
        this.vehicleIdentifier = vehicleIdentifier;
        this.category = category;
        this.costPerDay=costPerDay;
    }
}
