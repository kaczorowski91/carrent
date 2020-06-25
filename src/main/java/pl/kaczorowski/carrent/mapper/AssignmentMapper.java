package pl.kaczorowski.carrent.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.kaczorowski.carrent.dto.AssignmentDto;
import pl.kaczorowski.carrent.entity.Assignment;
import pl.kaczorowski.carrent.entity.User;
import pl.kaczorowski.carrent.entity.Vehicle;
import pl.kaczorowski.carrent.service.UserService;
import pl.kaczorowski.carrent.service.VehicleService;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AssignmentMapper {

    @Autowired
    private UserService userService;

    @Autowired
    private VehicleService vehicleService;

    public Assignment mapToAssignment(AssignmentDto assignmentDto) {
        User user = userService.getUser(assignmentDto.getUserId());
        Vehicle vehicle = vehicleService.getVehicle(assignmentDto.getVehicleId());
        return new Assignment(
                assignmentDto.getId(),
                assignmentDto.getBegin(),
                assignmentDto.getAppointedEnd(),
                assignmentDto.getRealEnd(),
                user,
                vehicle);
    }

    public AssignmentDto mapToAssignmentDto(Assignment assignment) {
        return new AssignmentDto(
                assignment.getId(),
                assignment.getBegin(),
                assignment.getAppointedEnd(),
                assignment.getRealEnd(),
                assignment.getUser().getId(),
                assignment.getVehicle().getId(),
                assignment.getPlannedCost(),
                assignment.getRealCost());
    }

    public List<AssignmentDto> mapToAssignmentDtoList(List<Assignment> assignments) {
        return assignments.stream()
                .map(this::mapToAssignmentDto)
                .collect(Collectors.toList());

    }

}
