package pl.kaczorowski.carrent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kaczorowski.carrent.dto.VehicleAssignmentDto;
import pl.kaczorowski.carrent.dto.VehicleDto;
import pl.kaczorowski.carrent.entity.Vehicle;
import pl.kaczorowski.carrent.exception.EntityAlreadyExistsException;
import pl.kaczorowski.carrent.exception.EntityNotFoundException;
import pl.kaczorowski.carrent.exception.ExceptionType;
import pl.kaczorowski.carrent.mapper.VehicleAssignmentMapper;
import pl.kaczorowski.carrent.mapper.VehicleMapper;
import pl.kaczorowski.carrent.repository.VehicleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleMapper vehicleMapper;

    public List<VehicleDto> getVehicles() {
        return vehicleMapper.mapToVehicleDtoList(vehicleRepository.findAll());
    }

    public VehicleDto getVehicle(Long id) {
        return vehicleMapper.mapToVehicleDto(vehicleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionType.VEHICLE_NOT_FOUND, id.toString())));
    }

    public List<VehicleDto> getVehicleByName(String name) {
        return vehicleMapper.mapToVehicleDtoList(vehicleRepository.findByName(name));
    }

    public Vehicle createVehicle(VehicleDto vehicleDto) {
        Vehicle vehicle = vehicleMapper.mapToVehicle(vehicleDto);
        if (vehicleRepository.findByVehicleIdentifier(vehicle.getVehicleIdentifier()) != null) {
            throw new EntityAlreadyExistsException(ExceptionType.VEHICLE_NOT_CHANGE, vehicle.getVehicleIdentifier());
        }
        return vehicleRepository.save(vehicle);
    }

    public Vehicle updateVehicle(VehicleDto vehicleDto) {
        Vehicle vehicle = vehicleMapper.mapToVehicle(vehicleDto);
        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(Long id) {
        getVehicle(id);
        vehicleRepository.deleteById(id);
    }

    public List<VehicleAssignmentDto> getVehicleAssignments(Long vehicleId) {
        return vehicleRepository.findById(vehicleId)
                .map(Vehicle::getAssignments)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionType.VEHICLE_NOT_FOUND, vehicleId.toString()))
                .stream()
                .map(VehicleAssignmentMapper::mapToVehicleAssignmentDto)
                .collect(Collectors.toList());
    }


}
