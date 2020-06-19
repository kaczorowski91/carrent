package pl.kaczorowski.carrent.mapper;

import org.springframework.stereotype.Component;
import pl.kaczorowski.carrent.dto.UserAssignmentDto;
import pl.kaczorowski.carrent.entity.Assignment;

@Component
public class UserAssignmentMapper {

    public static UserAssignmentDto mapToUserAssigmentDto(final Assignment assignment){
        return new UserAssignmentDto(
                assignment.getId(),
                assignment.getBegin(),
                assignment.getAppointedEnd(),
                assignment.getRealEnd(),
                assignment.getVehicle().getId(),
                assignment.getVehicle().getName(),
                assignment.getVehicle().getVehicleIdentifier());
    }
}
