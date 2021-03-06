package pl.kaczorowski.carrent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.kaczorowski.carrent.entity.Assignment;

@Repository
@Transactional
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

}
