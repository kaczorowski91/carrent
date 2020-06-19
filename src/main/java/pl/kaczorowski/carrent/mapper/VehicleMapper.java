package pl.kaczorowski.carrent.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.kaczorowski.carrent.dto.VehicleDto;
import pl.kaczorowski.carrent.entity.Vehicle;
import pl.kaczorowski.carrent.entity.VehicleCategory;
import pl.kaczorowski.carrent.repository.VehicleCategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class VehicleMapper {

    @Autowired
    VehicleCategoryRepository vehicleCategoryRepository;

    public Vehicle mapToVehicle(final VehicleDto vehicleDto) {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleDto.getId());
        vehicle.setName(vehicleDto.getName());
        vehicle.setVehicleIdentifier(vehicleDto.getVehicleIdentifier());
        Optional <VehicleCategory> vehicleCategory = vehicleCategoryRepository.findByName(vehicleDto.getCategory());
        vehicleCategory.ifPresent(vehicle::setCategory);
        return vehicle;
    }

    public VehicleDto mapToVehicleDto (final Vehicle vehicle){
        return new VehicleDto(
                vehicle.getId(),
                vehicle.getName(),
                vehicle.getVehicleIdentifier(),
                vehicle.getCategory().getName());
    }

    public List<VehicleDto> mapToVehicleDtoList(List<Vehicle>vehicles){
        return vehicles.stream()
                .map(this::mapToVehicleDto)
                .collect(Collectors.toList());
    }

}
