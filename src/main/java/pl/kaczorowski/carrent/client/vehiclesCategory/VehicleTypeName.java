package pl.kaczorowski.carrent.client.vehiclesCategory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class VehicleTypeName {

    @JsonProperty("VehicleTypeName")
    private String VehicleTypeName;

    @Override
    public String toString() {
        return VehicleTypeName;
    }
}
