package pl.kaczorowski.carrent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.kaczorowski.carrent.dto.VehicleDto;
import pl.kaczorowski.carrent.mapper.VehicleMapper;
import pl.kaczorowski.carrent.service.VehicleService;

import java.util.List;

@RestController
@RequestMapping("v1/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleMapper vehicleMapper;

    @GetMapping
    public List<VehicleDto> getVehicles() {
        return vehicleMapper.mapToVehicleDtoList(vehicleService.getVehicles());
    }

    @GetMapping("/{id}")
    public VehicleDto getVehicle(@PathVariable Long id) {
        return vehicleMapper.mapToVehicleDto(vehicleService.getVehicle(id));
    }

    @GetMapping("/name")
    public List<VehicleDto> getVehicles(@RequestParam String name) {
        return vehicleMapper.mapToVehicleDtoList(vehicleService.getVehicleByName(name));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public VehicleDto createVehicle(@RequestBody VehicleDto vehicleDto) {
        return vehicleMapper.mapToVehicleDto(vehicleService.createVehicle(vehicleMapper.mapToVehicle(vehicleDto)));
    }

    @PutMapping
    public VehicleDto updateVehicle(@RequestBody VehicleDto vehicleDto) {
        return vehicleMapper.mapToVehicleDto(vehicleService.updateVehicle(vehicleMapper.mapToVehicle(vehicleDto)));
    }

    @DeleteMapping("/{id}")
    public void deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
    }

}
