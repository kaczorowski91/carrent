package pl.kaczorowski.carrent.mapper;

import org.springframework.stereotype.Component;
import pl.kaczorowski.carrent.dto.AssignmentDto;
import pl.kaczorowski.carrent.entity.Assignment;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AssignmentMapper {

    public static AssignmentDto mapToAssignmentDtoStatic(Assignment assignment) {
        return new AssignmentDto(
                assignment.getId(),
                assignment.getBegin(),
                assignment.getAppointedEnd(),
                assignment.getRealEnd(),
                assignment.getUser().getId(),
                assignment.getVehicle().getId());
    }
    public AssignmentDto mapToAssignmentDto(Assignment assignment) {
        return new AssignmentDto(
                assignment.getId(),
                assignment.getBegin(),
                assignment.getAppointedEnd(),
                assignment.getRealEnd(),
                assignment.getUser().getId(),
                assignment.getVehicle().getId());
    }

    public List<AssignmentDto> mapToAssignmentDtoList(List<Assignment> assignments) {
        return assignments.stream()
                .map(this::mapToAssignmentDto)
                .collect(Collectors.toList());

    }


}
