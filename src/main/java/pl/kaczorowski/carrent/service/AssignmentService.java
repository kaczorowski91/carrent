package pl.kaczorowski.carrent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kaczorowski.carrent.dto.AssignmentDto;
import pl.kaczorowski.carrent.entity.Assignment;
import pl.kaczorowski.carrent.entity.User;
import pl.kaczorowski.carrent.entity.Vehicle;
import pl.kaczorowski.carrent.exception.AssignmentAlreadyFinishedException;
import pl.kaczorowski.carrent.exception.EntityNotFoundException;
import pl.kaczorowski.carrent.exception.ExceptionType;
import pl.kaczorowski.carrent.repository.AssignmentRepository;
import pl.kaczorowski.carrent.repository.UserRepository;
import pl.kaczorowski.carrent.repository.VehicleRepository;

import javax.transaction.Transactional;
import java.time.Duration;
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

    public List<Assignment> getAssignments() {
        List<Assignment> assignmentList = assignmentRepository.findAll();
        for (Assignment assignment : assignmentList) {
            calculateBill(assignment);
        }
        return assignmentList;
    }

    public Assignment getAssignment(Long id) {
        Assignment assignment = assignmentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ExceptionType.ASSIGNMENT_NOT_FOUND, id.toString()));
        calculateBill(assignment);
        return assignment;
    }

    public Assignment saveAssignment(AssignmentDto assignmentDto) {
        Optional<User> user = userRepository.findById(assignmentDto.getUserId());
        Optional<Vehicle> vehicle = vehicleRepository.findById(assignmentDto.getVehicleId());
        Assignment assignment = new Assignment();
        Long userId = assignmentDto.getUserId();
        Long vehicleId = assignmentDto.getVehicleId();
        assignment.setBegin(LocalDateTime.now());
        assignment.setAppointedEnd(assignmentDto.getAppointedEnd());
        assignment.setRealEnd(assignmentDto.getRealEnd());
        assignment.setUser(user.orElseThrow(() -> new EntityNotFoundException(ExceptionType.USER_NOT_FOUND, userId.toString())));
        assignment.setVehicle(vehicle.orElseThrow(() -> new EntityNotFoundException(ExceptionType.VEHICLE_NOT_FOUND, vehicleId.toString())));

        Duration durationPlannedCost = Duration.between(assignment.getBegin(), assignment.getAppointedEnd());
        Duration durationRealCost = Duration.between(assignment.getBegin(), assignment.getRealEnd());
        assignment.setPlannedCost(durationPlannedCost.toDays() * vehicle.get().getCostPerDay());
        assignment.setRealCost(durationRealCost.toDays() * vehicle.get().getCostPerDay());
        return assignmentRepository.save(assignment);
    }

    public Assignment updateAssignment(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }

    @Transactional
    public LocalDateTime endDateUpateAssignment(Long assignmentId) {
        Optional<Assignment> assignment = assignmentRepository.findById(assignmentId);
        Assignment assigmentToSave = assignment.orElseThrow(() -> new EntityNotFoundException(ExceptionType.ASSIGNMENT_NOT_FOUND, assignmentId.toString()));
        if (assigmentToSave.getRealEnd() != null)
            throw new AssignmentAlreadyFinishedException();
        else
            assigmentToSave.setRealEnd(LocalDateTime.now());
        return assigmentToSave.getRealEnd();
    }

    public void deleteAssignment(Long id) {
        getAssignment(id);
        assignmentRepository.deleteById(id);
    }

    private void calculateBill(Assignment assignment) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(assignment.getVehicle().getId());
        if (assignment.getAppointedEnd() != null) {
            Duration durationPlannedCost = Duration.between(assignment.getBegin(), assignment.getAppointedEnd());
            assignment.setPlannedCost(durationPlannedCost.toDays() * vehicle.get().getCostPerDay());
        }
        if (assignment.getRealEnd() != null) {
            Duration durationRealCost = Duration.between(assignment.getBegin(), assignment.getRealEnd());
            assignment.setRealCost(durationRealCost.toDays() * vehicle.get().getCostPerDay());
        }
    }
}