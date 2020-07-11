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
import java.util.ArrayList;
import java.util.List;

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
        User user1 = new User("TestFirstname", "TestLastName", "00087");
        userRepository.save(user1);
        VehicleCategory vehicleCategory1 = new VehicleCategory("truck");
        vehicleCategoryRepository.save(vehicleCategory1);
        Vehicle vehicle1 = new Vehicle("Lambo", "006565", vehicleCategory1, 50.50);
        vehicleRepository.save(vehicle1);

        AssignmentDto assignmentDto = new AssignmentDto(LocalDateTime.now(), LocalDateTime.now().plusDays(10),
                LocalDateTime.now().plusDays(20), user1.getId(), vehicle1.getId());
        //Then

        System.out.println(vehicleRepository.findByName(vehicle1.getName()));
        Assignment assignment = assignmentMapper.mapToAssignment(assignmentDto);
        //When
        Assert.assertEquals(assignment.getBegin(), assignmentDto.getBegin());
        Assert.assertEquals(assignment.getAppointedEnd(), assignmentDto.getAppointedEnd());
        Assert.assertEquals(assignment.getRealEnd(), assignmentDto.getRealEnd());
        Assert.assertEquals(assignment.getUser().getId(), assignmentDto.getUserId());
        Assert.assertEquals(assignment.getVehicle().getId(), assignmentDto.getVehicleId());

    }

    @Test
    public void mapToAssignmentDtoTest() {
        //Given
        User user = new User("firstName", "LastName", "00043");
        userRepository.save(user);
        VehicleCategory vehicleCategory = new VehicleCategory("car");
        vehicleCategoryRepository.save(vehicleCategory);
        Vehicle vehicle = new Vehicle("BMW", "00004341", vehicleCategory, 520.50);
        vehicleRepository.save(vehicle);
        Assignment assignment = new Assignment(LocalDateTime.now(), LocalDateTime.now().plusDays(10),
                LocalDateTime.now().plusDays(20), user, vehicle);
        //Then
        AssignmentDto assignmentDto = assignmentMapper.mapToAssignmentDto(assignment);
        //When
        Assert.assertEquals(assignmentDto.getBegin(), assignment.getBegin());
        Assert.assertEquals(assignmentDto.getAppointedEnd(), assignment.getAppointedEnd());
        Assert.assertEquals(assignmentDto.getRealEnd(), assignment.getRealEnd());
        Assert.assertEquals(assignmentDto.getUserId(), assignment.getUser().getId());
        Assert.assertEquals(assignmentDto.getVehicleId(), assignment.getVehicle().getId());

    }

    @Test
    public void mapToAssignmentDtoListTest() {
        //Given
        User user1 = new User("TestFirstname1", "TestLastName1", "5050");
        User user2 = new User("TestFirstname2", "TestLastName2", "842151");
        User user3 = new User("TestFirstname3", "TestLastName3", "284436");
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        VehicleCategory vehicleCategory = new VehicleCategory("car");
        vehicleCategoryRepository.save(vehicleCategory);
        Vehicle vehicle1 = new Vehicle("BMW", "01", vehicleCategory, 505.50);
        Vehicle vehicle2 = new Vehicle("FORD", "10", vehicleCategory, 21.50);
        Vehicle vehicle3 = new Vehicle("MERC", "6598", vehicleCategory, 50.50);
        vehicleRepository.save(vehicle1);
        vehicleRepository.save(vehicle2);
        vehicleRepository.save(vehicle3);
        Assignment assignment1 = new Assignment(LocalDateTime.now(), LocalDateTime.now().plusDays(10),
                LocalDateTime.now().plusDays(210), user1, vehicle1);
        Assignment assignment2 = new Assignment(LocalDateTime.now(), LocalDateTime.now().plusDays(120),
                LocalDateTime.now().plusDays(230), user2, vehicle2);
        Assignment assignment3 = new Assignment(LocalDateTime.now(), LocalDateTime.now().plusDays(310),
                LocalDateTime.now().plusDays(240), user3, vehicle3);
        List<Assignment> assignmentList = new ArrayList<>();
        assignmentList.add(assignment1);
        assignmentList.add(assignment2);
        assignmentList.add(assignment3);
        List<AssignmentDto> assignmentDtoList = assignmentMapper.mapToAssignmentDtoList(assignmentList);
        int count = assignmentList.size();
        //Then
        Assert.assertEquals(assignmentDtoList.size(), count);
        Assert.assertEquals(assignmentDtoList.get(0).getBegin(), assignmentList.get(0).getBegin());
        Assert.assertEquals(assignmentDtoList.get(1).getUserId(), assignmentList.get(1).getUser().getId());
        Assert.assertEquals(assignmentDtoList.get(2).getVehicleId(), assignmentList.get(2).getVehicle().getId());

    }
}