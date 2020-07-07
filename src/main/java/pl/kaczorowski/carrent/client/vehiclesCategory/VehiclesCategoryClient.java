package pl.kaczorowski.carrent.client.vehiclesCategory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class VehiclesCategoryClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(VehiclesCategoryClient.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CategoryConfig categoryConfig;

    public CategoryDto getCategory() {
        try {
            CategoryDto response = restTemplate.getForObject(getCategoryUrl(), CategoryDto.class);
            return response;
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    private URI getCategoryUrl() {
        return UriComponentsBuilder.fromHttpUrl(categoryConfig.getVehicleCategory())
                .build().encode().toUri();
    }


}
