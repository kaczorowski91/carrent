package pl.kaczorowski.carrent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.kaczorowski.carrent.entity.VehicleCategory;

import java.util.Optional;

@Repository
@Transactional
public interface VehicleCategoryRepository extends JpaRepository<VehicleCategory, Long> {

    Optional<VehicleCategory> findByName(String name);
}
