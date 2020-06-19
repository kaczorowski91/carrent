package pl.kaczorowski.carrent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kaczorowski.carrent.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByPesel(String pesel);
}
