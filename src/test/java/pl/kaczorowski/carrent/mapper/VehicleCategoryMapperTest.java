package pl.kaczorowski.carrent.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.kaczorowski.carrent.dto.VehicleCategoryDto;
import pl.kaczorowski.carrent.entity.VehicleCategory;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VehicleCategoryMapperTest {

    @Autowired
    private VehicleCategoryMapper vehicleCategoryMapper;

    @Test
    public void mapToVehicleCategoryDtoTest() {
        //Given
        VehicleCategory vehicleCategory = new VehicleCategory("car");
        //Then
        VehicleCategoryDto  vehicleCategoryDto= vehicleCategoryMapper.mapToVehicleCategoryDto(vehicleCategory);
        //When
        Assert.assertEquals(vehicleCategory.getName(),vehicleCategoryDto.getName());
    }
}