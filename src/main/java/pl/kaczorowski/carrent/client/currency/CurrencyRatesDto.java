package pl.kaczorowski.carrent.client.currency;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class CurrencyRatesDto {
    @JsonProperty("EUR")
    private BigDecimal eur;
    @JsonProperty("USD")
    private BigDecimal usd;
}
