package pl.kaczorowski.carrent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kaczorowski.carrent.client.currency.CurrencyClient;
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
    @Autowired
    private CurrencyClient currencyClient;

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
        assignment.setPlannedCostPLN(durationPlannedCost.toDays() * vehicle.get().getCostPerDay());
        assignment.setRealCostPLN(durationRealCost.toDays() * vehicle.get().getCostPerDay());
        return assignmentRepository.save(assignment);
    }

    public Assignment updateAssignment(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }

    @Transactional
    public LocalDateTime endDateUpdateAssignment(Long assignmentId) {
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

    public void calculateBill(Assignment assignment) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(assignment.getVehicle().getId());
        double getEurCurrencyRate=currencyClient.getCommercialRates().getRates().getEur();
        double getDolCurrencyRate=currencyClient.getCommercialRates().getRates().getUsd();

        if (assignment.getAppointedEnd() != null) {
            Duration durationPlannedCost = Duration.between(assignment.getBegin(), assignment.getAppointedEnd());

            double plannedCostPLN = durationPlannedCost.toDays() * vehicle.get().getCostPerDay();
            plannedCostPLN =Math.round(plannedCostPLN*100);
            assignment.setPlannedCostPLN(plannedCostPLN/100);

            double plannedCostEUR = assignment.getPlannedCostPLN()*getEurCurrencyRate;
            plannedCostEUR=Math.round(plannedCostEUR*100);
            assignment.setPlannedCostEUR(plannedCostEUR/100);

            double plannedCostDOL = assignment.getPlannedCostPLN()*getDolCurrencyRate;
            plannedCostDOL=Math.round(plannedCostDOL*100);
            assignment.setPlannedCostDOL(plannedCostDOL/100);

        }
        if (assignment.getRealEnd() != null) {
            Duration durationRealCost = Duration.between(assignment.getBegin(), assignment.getRealEnd());

            double realCostPLN = durationRealCost.toDays() * vehicle.get().getCostPerDay();
            realCostPLN = Math.round(realCostPLN*100);
            assignment.setRealCostPLN(realCostPLN/100);

            double realCostEUR = getEurCurrencyRate*assignment.getRealCostPLN();
            realCostEUR= Math.round(realCostEUR*100);
            assignment.setRealCostEUR(realCostEUR/100);

            double realCostDOL=getDolCurrencyRate*assignment.getRealCostPLN();
            realCostDOL=Math.round(realCostDOL*100);
            assignment.setRealCostDOL(realCostDOL/100);

        }
    }
}