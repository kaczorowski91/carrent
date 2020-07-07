package pl.kaczorowski.carrent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kaczorowski.carrent.client.currency.CurrencyClient;
import pl.kaczorowski.carrent.client.currency.CurrencyDto;

@RestController
@RequestMapping("/v1/currency")
public class CurrencyController {

    @Autowired
    CurrencyClient currencyClient;

    @GetMapping
    public CurrencyDto getCurrency(){
        return currencyClient.getCommercialRates();
    }

}
