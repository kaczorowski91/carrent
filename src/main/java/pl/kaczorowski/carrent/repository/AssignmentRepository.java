package pl.kaczorowski.carrent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kaczorowski.carrent.entity.Assignment;

import java.util.Optional;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    Optional<Assignment>findByVehicle_idAndRealEndIsNull(Long vehicleId);
}
