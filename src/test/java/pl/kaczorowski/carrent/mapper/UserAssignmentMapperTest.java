package pl.kaczorowski.carrent.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.kaczorowski.carrent.dto.UserAssignmentDto;
import pl.kaczorowski.carrent.entity.Assignment;
import pl.kaczorowski.carrent.entity.User;
import pl.kaczorowski.carrent.entity.Vehicle;
import pl.kaczorowski.carrent.entity.VehicleCategory;
import pl.kaczorowski.carrent.repository.AssignmentRepository;
import pl.kaczorowski.carrent.repository.UserRepository;
import pl.kaczorowski.carrent.repository.VehicleCategoryRepository;
import pl.kaczorowski.carrent.repository.VehicleRepository;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserAssignmentMapperTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    VehicleCategoryRepository vehicleCategoryRepository;

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    AssignmentRepository assignmentRepository;

    @Test
    public void mapToUserAssignmentDtoTest() {
        //Given
        User user = new User("TestFirstname", "TestLastName", "000");
        userRepository.save(user);
        VehicleCategory vehicleCategory = new VehicleCategory("car");
        vehicleCategoryRepository.save(vehicleCategory);
        Vehicle vehicle1 = new Vehicle("BMW", "00001", vehicleCategory, 50.50);
        vehicleRepository.save(vehicle1);
        Assignment assignment1 = new Assignment(LocalDateTime.now(), LocalDateTime.now().plusDays(5),
                LocalDateTime.now().plusDays(10), user, vehicle1);
        assignmentRepository.save(assignment1);
        //Then
        UserAssignmentDto userAssignmentDto = UserAssignmentMapper.mapToUserAssignmentDto(assignment1);
        //When
        Assert.assertEquals(userAssignmentDto.getBegin(), assignment1.getBegin());
        Assert.assertEquals(userAssignmentDto.getAppointedEnd(), assignment1.getAppointedEnd());
        Assert.assertEquals(userAssignmentDto.getRealEnd(), assignment1.getRealEnd());
        Assert.assertEquals(userAssignmentDto.getVehicleId(), assignment1.getVehicle().getId());
        Assert.assertEquals(userAssignmentDto.getVehicleName(), assignment1.getVehicle().getName());
        Assert.assertEquals(userAssignmentDto.getVehicleIdentifier(), assignment1.getVehicle().getVehicleIdentifier());
        Assert.assertEquals(userAssignmentDto.getPlannedCost(), assignment1.getPlannedCostPLN(), 0);
        Assert.assertEquals(userAssignmentDto.getRealCost(), assignment1.getRealCostPLN(), 0);

    }
}