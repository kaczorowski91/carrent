package pl.kaczorowski.carrent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.kaczorowski.carrent.entity.Vehicle;

import java.util.List;

@Repository
@Transactional
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findByName(String name);

    Vehicle findByVehicleIdentifier(String vehicleIdentifier);
}
