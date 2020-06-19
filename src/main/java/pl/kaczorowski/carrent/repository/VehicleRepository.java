package pl.kaczorowski.carrent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kaczorowski.carrent.entity.Vehicle;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findByName(String name);

    Vehicle findByVehicleIdentifier(String vehicleIdentifier);
}
