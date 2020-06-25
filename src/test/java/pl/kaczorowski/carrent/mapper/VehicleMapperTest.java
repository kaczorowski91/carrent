package pl.kaczorowski.carrent.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.kaczorowski.carrent.dto.VehicleDto;
import pl.kaczorowski.carrent.entity.Vehicle;
import pl.kaczorowski.carrent.entity.VehicleCategory;
import pl.kaczorowski.carrent.exception.EntityNotFoundException;
import pl.kaczorowski.carrent.exception.ExceptionType;
import pl.kaczorowski.carrent.repository.VehicleCategoryRepository;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VehicleMapperTest {

    @Autowired
    VehicleMapper vehicleMapper;

    @Autowired
    VehicleCategoryRepository vehicleCategoryRepository;

    @Test
    public void mapToVehicleTest() {
        //Given
        VehicleCategory vehicleCategory = new VehicleCategory("car");
        vehicleCategoryRepository.save(vehicleCategory);
        VehicleDto vehicleDto = new VehicleDto("BMW", "00001", vehicleCategory.getName(), 50.50);
        //Then
        Vehicle vehicle = vehicleMapper.mapToVehicle(vehicleDto);
        //When
        Assert.assertEquals(vehicleDto.getName(), vehicle.getName());
        Assert.assertEquals(vehicleDto.getVehicleIdentifier(), vehicle.getVehicleIdentifier());
        Assert.assertEquals(vehicleDto.getCategory(), vehicle.getCategory().getName());
        Assert.assertEquals(vehicleDto.getCostPerDay(), vehicle.getCostPerDay(), 0);

    }

    @Test
    public void mapToVehicleDtoTest() {
        //Given
        VehicleCategory vehicleCategory = new VehicleCategory("car");
        Vehicle vehicle = new Vehicle("BMW", "00001", vehicleCategory, 50.50);
        //Then
        VehicleDto vehicleDto = vehicleMapper.mapToVehicleDto(vehicle);
        //When
        Assert.assertEquals(vehicle.getName(),vehicleDto.getName());
        Assert.assertEquals(vehicle.getVehicleIdentifier(),vehicleDto.getVehicleIdentifier());
        Assert.assertEquals(vehicle.getCategory().getName(),vehicleDto.getCategory());
        Assert.assertEquals(vehicle.getCostPerDay(),vehicleDto.getCostPerDay(),0);
    }

    @Test
    public void mapToVehicleDtoListTest() {
        //Given
        VehicleCategory vehicleCategory = new VehicleCategory("car");
        Vehicle vehicle1 = new Vehicle("BMW", "00001", vehicleCategory, 50.50);
        Vehicle vehicle2 = new Vehicle("AUDI", "00002", vehicleCategory, 100.55);
        Vehicle vehicle3 = new Vehicle("RENAULT", "00003", vehicleCategory, 5.50);
        Vehicle vehicle4 = new Vehicle("FORD", "00004", vehicleCategory, 5000.50);
        //Then
        List<Vehicle> vehicleList=new ArrayList<>();
        vehicleList.add(vehicle1);
        vehicleList.add(vehicle2);
        vehicleList.add(vehicle3);
        vehicleList.add(vehicle4);
        List<VehicleDto>vehicleDtoList=vehicleMapper.mapToVehicleDtoList(vehicleList);
        int count =vehicleList.size();
        //When
        Assert.assertEquals(vehicleDtoList.size(),count);
        Assert.assertEquals(vehicleDtoList.get(0).getName(),vehicleList.get(0).getName());
        Assert.assertEquals(vehicleDtoList.get(1).getCategory(),vehicleList.get(1).getCategory().getName());
        Assert.assertEquals(vehicleDtoList.get(2).getVehicleIdentifier(),vehicleList.get(2).getVehicleIdentifier());
        Assert.assertEquals(vehicleDtoList.get(3).getCostPerDay(),vehicleList.get(3).getCostPerDay(),0);

    }
}