package pl.kaczorowski.carrent.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.kaczorowski.carrent.dto.AssignmentDto;
import pl.kaczorowski.carrent.entity.Assignment;
import pl.kaczorowski.carrent.entity.User;
import pl.kaczorowski.carrent.entity.Vehicle;
import pl.kaczorowski.carrent.entity.VehicleCategory;
import pl.kaczorowski.carrent.repository.UserRepository;
import pl.kaczorowski.carrent.repository.VehicleCategoryRepository;
import pl.kaczorowski.carrent.repository.VehicleRepository;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AssignmentMapperTest {

    @Autowired
    private AssignmentMapper assignmentMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleCategoryRepository vehicleCategoryRepository;

    @Test
    public void mapToAssignmentTest() {
        //Given
        User user = new User("TestFirstname", "TestLastName", "000");
        userRepository.save(user);
        VehicleCategory vehicleCategory = new VehicleCategory("car");
        vehicleCategoryRepository.save(vehicleCategory);
        Vehicle vehicle = new Vehicle("BMW", "00001", vehicleCategory, 50.50);
        vehicleRepository.save(vehicle);

        AssignmentDto assignmentDto = new AssignmentDto(LocalDateTime.now(), LocalDateTime.now().plusDays(10),
                LocalDateTime.now().plusDays(20), user.getId(), vehicle.getId());

        System.out.println(assignmentDto.getBegin()+" "+ assignmentDto.getAppointedEnd());

        //Assignment assignment=assignmentMapper.mapToAssignment(assignmentDto);
        //Then

        //When
      //  Assert.assertEquals(assignment.getBegin(),assignmentDto.getBegin());


    }

    @Test
    public void mapToAssignmentDtoTest() {
        Assignment assignment = new Assignment();
    }

    @Test
    public void mapToAssignmentDtoListTest() {
    }
}