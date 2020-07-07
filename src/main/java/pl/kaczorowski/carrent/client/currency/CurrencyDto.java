package pl.kaczorowski.carrent.client.currency;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyDto {
    @JsonProperty("rates")
    private CurrencyRatesDto rates;
}

