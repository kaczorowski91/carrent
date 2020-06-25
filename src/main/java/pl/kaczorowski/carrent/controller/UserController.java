package pl.kaczorowski.carrent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.kaczorowski.carrent.dto.UserAssignmentDto;
import pl.kaczorowski.carrent.dto.UserDto;
import pl.kaczorowski.carrent.mapper.UserMapper;
import pl.kaczorowski.carrent.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping
    public List<UserDto> getUsers() {
        return userMapper.mapToUserDtoList(userService.getUsers());
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return userMapper.mapToUserDto(userService.getUser(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userMapper.mapToUserDto(userService.createUser(userMapper.mapToUser(userDto)));
    }

    @PutMapping
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return userMapper.mapToUserDto(userService.updateUser(userMapper.mapToUser(userDto)));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/{id}/assignments")
    public List<UserAssignmentDto> getUserAssignments(@PathVariable Long id) {
        return userService.getUserAssignments(id);
    }
}

