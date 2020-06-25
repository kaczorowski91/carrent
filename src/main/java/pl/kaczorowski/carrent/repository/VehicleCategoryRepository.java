package pl.kaczorowski.carrent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kaczorowski.carrent.entity.VehicleCategory;

import java.util.Optional;

public interface VehicleCategoryRepository extends JpaRepository<VehicleCategory, Long> {

    Optional<VehicleCategory> findByName(String name);
}
