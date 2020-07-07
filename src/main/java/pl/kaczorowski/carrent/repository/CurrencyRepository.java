package pl.kaczorowski.carrent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.kaczorowski.carrent.entity.Currency;

@Repository
@Transactional
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}
