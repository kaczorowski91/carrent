package pl.kaczorowski.carrent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.kaczorowski.carrent.entity.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    User findByPesel(String pesel);
}
