package pl.kaczorowski.carrent.client.currency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static java.util.Optional.ofNullable;

@Component
public class CurrencyClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyClient.class);

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    CurrencyConfig currencyConfig;


    public CurrencyDto getCommercialRates() {
        try {
            CurrencyDto response = restTemplate.getForObject(getCommercialUrl(), CurrencyDto.class);
            return ofNullable(response).orElseGet(CurrencyDto::new);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new CurrencyDto();
        }
    }

    private URI getCommercialUrl() {
        return UriComponentsBuilder.fromHttpUrl(currencyConfig.getBaseCommercialUrl())
                .queryParam("base", currencyConfig.getNationalCurrency())
                .build().encode().toUri();
    }

    @Override
    public String toString() {
        return "CurrencyClient{" +
                "restTemplate=" + restTemplate +
                ", currencyConfig=" + currencyConfig +
                '}';
    }
}
