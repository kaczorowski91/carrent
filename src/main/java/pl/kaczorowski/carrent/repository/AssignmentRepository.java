package pl.kaczorowski.carrent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kaczorowski.carrent.entity.Assignment;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

}
