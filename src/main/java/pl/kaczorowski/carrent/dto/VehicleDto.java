package pl.kaczorowski.carrent.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDto {
    private Long id;
    private String name;
    private String vehicleIdentifier;
    private String category;
    private double costPerDay;

    public VehicleDto(String name, String vehicleIdentifier, String category, double costPerDay) {
        this.name = name;
        this.vehicleIdentifier = vehicleIdentifier;
        this.category = category;
        this.costPerDay = costPerDay;
    }
}
