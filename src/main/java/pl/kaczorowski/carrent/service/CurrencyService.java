package pl.kaczorowski.carrent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kaczorowski.carrent.repository.CurrencyRepository;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;





}
