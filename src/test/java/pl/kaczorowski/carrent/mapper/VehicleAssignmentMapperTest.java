package pl.kaczorowski.carrent.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.kaczorowski.carrent.dto.VehicleAssignmentDto;
import pl.kaczorowski.carrent.entity.Assignment;
import pl.kaczorowski.carrent.entity.User;
import pl.kaczorowski.carrent.entity.Vehicle;
import pl.kaczorowski.carrent.entity.VehicleCategory;
import pl.kaczorowski.carrent.repository.AssignmentRepository;
import pl.kaczorowski.carrent.repository.UserRepository;
import pl.kaczorowski.carrent.repository.VehicleCategoryRepository;
import pl.kaczorowski.carrent.repository.VehicleRepository;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VehicleAssignmentMapperTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    VehicleCategoryRepository vehicleCategoryRepository;

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    AssignmentRepository assignmentRepository;

    @Test
    public void mapToVehicleAssignmentDtoTest() {
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
        VehicleAssignmentDto vehicleAssignmentDto = VehicleAssignmentMapper.mapToVehicleAssignmentDto(assignment1);
        //When
        Assert.assertEquals(vehicleAssignmentDto.getBegin(), assignment1.getBegin());
        Assert.assertEquals(vehicleAssignmentDto.getAppointedEnd(), assignment1.getAppointedEnd());
        Assert.assertEquals(vehicleAssignmentDto.getRealEnd(), assignment1.getRealEnd());
        Assert.assertEquals(vehicleAssignmentDto.getUserId(), assignment1.getUser().getId());
        Assert.assertEquals(vehicleAssignmentDto.getUserId(), assignment1.getUser().getId());
        Assert.assertEquals(vehicleAssignmentDto.getUserFirstName(), assignment1.getUser().getFirstName());
        Assert.assertEquals(vehicleAssignmentDto.getUserLastName(), assignment1.getUser().getLastName());
        Assert.assertEquals(vehicleAssignmentDto.getPlannedCost(), assignment1.getPlannedCost(), 0);
        Assert.assertEquals(vehicleAssignmentDto.getRealCost(), assignment1.getRealCost(), 0);
    }
}