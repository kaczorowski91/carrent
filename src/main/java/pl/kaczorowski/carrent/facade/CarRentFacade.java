package pl.kaczorowski.carrent.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.kaczorowski.carrent.client.currency.CurrencyClient;
import pl.kaczorowski.carrent.client.currency.CurrencyDto;
import pl.kaczorowski.carrent.dto.*;
import pl.kaczorowski.carrent.entity.Assignment;
import pl.kaczorowski.carrent.entity.User;
import pl.kaczorowski.carrent.entity.Vehicle;
import pl.kaczorowski.carrent.mapper.UserMapper;
import pl.kaczorowski.carrent.service.AssignmentService;
import pl.kaczorowski.carrent.service.UserService;
import pl.kaczorowski.carrent.service.VehicleCategoryService;
import pl.kaczorowski.carrent.service.VehicleService;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class CarRentFacade {

    @Autowired
    AssignmentService assignmentService;

    @Autowired
    UserService userService;

    @Autowired
    VehicleCategoryService vehicleCategoryService;

    @Autowired
    VehicleService vehicleService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    CurrencyClient currencyClient;


    public List<Assignment> fetchAssigmnents() {
        return assignmentService.getAssignments();
    }

    public Assignment fetchAssignment(Long id) {
        return assignmentService.getAssignment(id);
    }

    public Assignment fetchAssignmentSave(AssignmentDto assignmentDto) {
        return assignmentService.saveAssignment(assignmentDto);
    }

    public Assignment fetchAssignmentUpdate(Assignment assignment) {
        return assignmentService.updateAssignment(assignment);
    }

    public void fetchAssignmentDelete(Long id) {
        assignmentService.deleteAssignment(id);
    }

    public LocalDateTime fetchEndDateUpdateAssigment(Long assignmentId) {
        return assignmentService.endDateUpdateAssignment(assignmentId);
    }

    public void fetchCalculateBill(Assignment assignment) {
        assignmentService.calculateBill(assignment);
    }

    public List<UserDto> fetchUsers() {
        return userService.getUsers();
    }

    public UserDto fetchUser(Long id) {
        return userMapper.mapToUserDto(userService.getUser(id));
    }

    public User fetchCreateUser(UserDto userDto) {
        return userService.createUser(userDto);
    }

    public User fetchUpdateUser(UserDto userDto) {
        return userService.updateUser(userDto);
    }

    public void fetchDeleteUser(Long id) {
        userService.deleteUser(id);
    }

    public List<UserAssignmentDto> fetchUserAssignments(Long id) {
        return userService.getUserAssignments(id);
    }

    public List<VehicleDto> fetchVehicles() {
        return vehicleService.getVehicles();
    }

    public VehicleDto fetchVehicle(Long id) {
        return vehicleService.getVehicle(id);
    }

    public List<VehicleDto> fetchVehicleByName(String name) {
        return vehicleService.getVehicleByName(name);
    }

    public Vehicle fetchCreateVehicle(VehicleDto vehicleDto) {
        return vehicleService.createVehicle(vehicleDto);
    }

    public Vehicle fetchUpdateVehicle(VehicleDto vehicleDto) {
        return vehicleService.updateVehicle(vehicleDto);
    }

    public void fetchDeleteVehicle(Long id) {
        vehicleService.deleteVehicle(id);
    }

    public List<VehicleAssignmentDto> fetchVehicleAssignments(long id) {
        return vehicleService.getVehicleAssignments(id);
    }

    public List<String> fetchGetCategories() {
        return vehicleCategoryService.getCategories();
    }

    public VehicleCategoryDto fetchgetCategory(Long id) {
        return vehicleCategoryService.getCategory(id);
    }

    public CurrencyDto fetchCurrency() {
        return currencyClient.getCommercialRates();
    }

}
