package pl.kaczorowski.carrent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.kaczorowski.carrent.client.currency.CurrencyDto;
import pl.kaczorowski.carrent.dto.*;
import pl.kaczorowski.carrent.facade.CarRentFacade;
import pl.kaczorowski.carrent.mapper.AssignmentMapper;
import pl.kaczorowski.carrent.mapper.UserMapper;
import pl.kaczorowski.carrent.mapper.VehicleMapper;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1/")
public class FacadeController {

    @Autowired
    private CarRentFacade carRentFacade;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VehicleMapper vehicleMapper;

    @Autowired
    private AssignmentMapper assignmentMapper;


    @GetMapping("user")
    public List<UserDto> getUsers() {
        return carRentFacade.fetchUsers();
    }

    @GetMapping("user/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return carRentFacade.fetchUser(id);
    }

    @PostMapping(path = "user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userMapper.mapToUserDto(carRentFacade.fetchCreateUser(userDto));
    }

    @PutMapping(path = "user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto updateUser(UserDto userDto) {
        return userMapper.mapToUserDto(carRentFacade.fetchUpdateUser(userDto));
    }

    @DeleteMapping("user/{id}")
    public void deleteUser(@PathVariable Long id) {
        carRentFacade.fetchDeleteUser(id);
    }

    @GetMapping("user/{id}/assignments")
    public List<UserAssignmentDto> getUserAssignments(@PathVariable Long id) {
        return carRentFacade.fetchUserAssignments(id);
    }

    @GetMapping("vehicle")
    public List<VehicleDto> getVehicles() {
        return carRentFacade.fetchVehicles();
    }

    @GetMapping("vehicle/{id}")
    public VehicleDto getVehicle(@PathVariable Long id) {
        return carRentFacade.fetchVehicle(id);
    }

    @GetMapping("vehicleByName")
    public List<VehicleDto> getVehicles(@RequestParam String name) {
        return carRentFacade.fetchVehicleByName(name);
    }

    @PostMapping(path = "vehicle", consumes = MediaType.APPLICATION_JSON_VALUE)
    public VehicleDto createVehicle(@RequestBody VehicleDto vehicleDto) {
        return vehicleMapper.mapToVehicleDto(carRentFacade.fetchCreateVehicle(vehicleDto));
    }

    @PutMapping(path = "vehicle", consumes = MediaType.APPLICATION_JSON_VALUE)
    public VehicleDto updateVehicle(@RequestBody VehicleDto vehicleDto) {
        return vehicleMapper.mapToVehicleDto(carRentFacade.fetchUpdateVehicle(vehicleDto));
    }

    @DeleteMapping("vehicle/{id}")
    public void deleteVehicle(@PathVariable Long id) {
        carRentFacade.fetchDeleteVehicle(id);
    }

    @GetMapping("vehicle/{id}/assignments")
    public List<VehicleAssignmentDto> getVehicleAssignments(@PathVariable Long id) {
        return carRentFacade.fetchVehicleAssignments(id);
    }

    @GetMapping("category")
    public List<String> findAllNames() {
        return carRentFacade.fetchGetCategories();
    }

    @GetMapping("category/{id}")
    public VehicleCategoryDto getCategory(@PathVariable Long id) {
        return carRentFacade.fetchgetCategory(id);
    }

    @GetMapping("assignment")
    public List<AssignmentDto> getAssignments() {
        return assignmentMapper.mapToAssignmentDtoList(carRentFacade.fetchAssigmnents());
    }

    @GetMapping("assignment/{id}")
    public AssignmentDto getAssignment(@PathVariable Long id) {
        return assignmentMapper.mapToAssignmentDto(carRentFacade.fetchAssignment(id));
    }

    @PostMapping(path = "assignment", consumes = MediaType.APPLICATION_JSON_VALUE)
    public AssignmentDto saveAssignment(@RequestBody AssignmentDto assignmentDto) {
        return assignmentMapper.mapToAssignmentDto(carRentFacade.fetchAssignmentSave(assignmentDto));
    }

    @DeleteMapping("assignment/{id}")
    public void deleteAssignment(@PathVariable Long id) {
        carRentFacade.fetchAssignmentDelete(id);
    }

    @PutMapping("assignment/{id}/end")
    public LocalDateTime updateAssignmentWithEndDate(@PathVariable Long id) {
        return carRentFacade.fetchEndDateUpdateAssigment(id);
    }

    @PutMapping(path = "assignment", consumes = MediaType.APPLICATION_JSON_VALUE)
    public AssignmentDto updateAssignment(@RequestBody AssignmentDto assignmentDto) {
        return assignmentMapper.mapToAssignmentDto(carRentFacade.fetchAssignmentUpdate(assignmentMapper.mapToAssignment(assignmentDto)));
    }

    @GetMapping(path="currency")
    public CurrencyDto getCurrency(){
        return carRentFacade.fetchCurrency();
    }


}
