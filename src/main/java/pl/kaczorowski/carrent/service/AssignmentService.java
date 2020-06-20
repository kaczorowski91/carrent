package pl.kaczorowski.carrent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kaczorowski.carrent.dto.AssignmentDto;
import pl.kaczorowski.carrent.entity.Assignment;
import pl.kaczorowski.carrent.entity.User;
import pl.kaczorowski.carrent.entity.Vehicle;
import pl.kaczorowski.carrent.exception.ExceptionType;
import pl.kaczorowski.carrent.exception.InvalidAssignmentException;
import pl.kaczorowski.carrent.mapper.AssignmentMapper;
import pl.kaczorowski.carrent.repository.AssignmentRepository;
import pl.kaczorowski.carrent.repository.UserRepository;
import pl.kaczorowski.carrent.repository.VehicleRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AssignmentService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private AssignmentRepository assignmentRepository;

    public List<Assignment>getAssignments(){return assignmentRepository.findAll();}

    AssignmentDto createAssignment(AssignmentDto assignmentDto) {
        Optional<Assignment> assignmentForVehicle = assignmentRepository.findByVehicle_idAndRealEndIsNull(assignmentDto.getVehicleId());
        assignmentForVehicle.ifPresent((assignment) -> {
            throw new InvalidAssignmentException(ExceptionType.VEHICLE_IS_RENTED, assignmentDto.getId().toString());
        });
        Optional<User> user = userRepository.findById(assignmentDto.getUserId());
        Optional<Vehicle> vehicle = vehicleRepository.findById(assignmentDto.getVehicleId());
        Assignment assignment = new Assignment();
        Long userId = assignmentDto.getUserId();
        Long vehicleId = assignmentDto.getVehicleId();
        assignment.setUser(user.orElseThrow(() -> new InvalidAssignmentException(ExceptionType.USER_NOT_FOUND, userId.toString())));
        assignment.setVehicle(vehicle.orElseThrow(() -> new InvalidAssignmentException(ExceptionType.VEHICLE_NOT_FOUND, vehicleId.toString())));
        assignment.setBegin(LocalDateTime.now());
        return AssignmentMapper.mapToAssignmentDtoStatic(assignmentRepository.save(assignment));

    }


}
