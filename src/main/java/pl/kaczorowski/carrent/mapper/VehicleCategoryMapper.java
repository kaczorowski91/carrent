package pl.kaczorowski.carrent.mapper;

import org.springframework.stereotype.Component;
import pl.kaczorowski.carrent.dto.VehicleCategoryDto;
import pl.kaczorowski.carrent.entity.VehicleCategory;

@Component
public class VehicleCategoryMapper {

    public VehicleCategoryDto mapToVehicleCategoryDto(VehicleCategory vehicleCategory) {
        return new VehicleCategoryDto(
                vehicleCategory.getId(),
                vehicleCategory.getName());
    }
}
