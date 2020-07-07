package pl.kaczorowski.carrent.client.vehiclesCategory;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class CategoryConfig {

    @Value("${vehicle.category}")
    private String vehicleCategory;

}
