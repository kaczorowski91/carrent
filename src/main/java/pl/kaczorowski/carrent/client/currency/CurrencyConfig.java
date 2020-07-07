package pl.kaczorowski.carrent.client.currency;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class CurrencyConfig {

    @Value("${nbp.base.url}")
    private String baseCommercialUrl;

    @Value("${nbp.national.currency}")
    private String nationalCurrency;
}
