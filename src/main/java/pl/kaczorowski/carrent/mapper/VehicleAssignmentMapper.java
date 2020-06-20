package pl.kaczorowski.carrent.mapper;

import pl.kaczorowski.carrent.dto.VehicleAssignmentDto;
import pl.kaczorowski.carrent.entity.Assignment;

public class VehicleAssignmentMapper {

    public static VehicleAssignmentDto mapToVehicleAssignmentDto(final Assignment assignment) {
        return new VehicleAssignmentDto(
                assignment.getId(),
                assignment.getBegin(),
                assignment.getAppointedEnd(),
                assignment.getRealEnd(),
                assignment.getUser().getId(),
                assignment.getUser().getFirstName(),
                assignment.getUser().getLastName(),
                assignment.getPlannedCost(),
                assignment.getRealCost());
    }
}
