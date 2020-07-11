package pl.kaczorowski.carrent.client.currency;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyRatesDto {
    @JsonProperty("EUR")
    private double eur;
    @JsonProperty("USD")
    private double usd;
}
