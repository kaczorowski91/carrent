package pl.kaczorowski.carrent.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String vehicleIdentifier;
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private VehicleCategory category;
    private double costPerDay;
    @OneToMany(mappedBy = "vehicle", fetch = FetchType.EAGER)
    private List<Assignment>assignments = new ArrayList<>();


    public Vehicle(String name, String vehicleIdentifier, VehicleCategory category, double costPerDay) {
        this.name = name;
        this.vehicleIdentifier = vehicleIdentifier;
        this.category = category;
        this.costPerDay = costPerDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;
        return Double.compare(vehicle.getCostPerDay(), getCostPerDay()) == 0 &&
                getId().equals(vehicle.getId()) &&
                getName().equals(vehicle.getName()) &&
                getVehicleIdentifier().equals(vehicle.getVehicleIdentifier()) &&
                Objects.equals(getAssignments(), vehicle.getAssignments());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getVehicleIdentifier(), getCostPerDay(), getAssignments());
    }
}
