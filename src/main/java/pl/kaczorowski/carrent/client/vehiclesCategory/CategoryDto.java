package pl.kaczorowski.carrent.client.vehiclesCategory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryDto {

    @JsonProperty("Results")
    private List<VehicleTypeName> results;

    @Override
    public String toString() {
        return "CategoryDto{" +
                "results=" + results;
    }
}
