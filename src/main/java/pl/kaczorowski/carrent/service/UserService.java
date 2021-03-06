package pl.kaczorowski.carrent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kaczorowski.carrent.dto.UserAssignmentDto;
import pl.kaczorowski.carrent.dto.UserDto;
import pl.kaczorowski.carrent.entity.User;
import pl.kaczorowski.carrent.exception.EntityAlreadyExistsException;
import pl.kaczorowski.carrent.exception.EntityNotFoundException;
import pl.kaczorowski.carrent.exception.ExceptionType;
import pl.kaczorowski.carrent.mapper.UserAssignmentMapper;
import pl.kaczorowski.carrent.mapper.UserMapper;
import pl.kaczorowski.carrent.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAssignmentMapper userAssignmentMapper;

    @Autowired
    private UserMapper userMapper;

    public List<UserDto> getUsers() {
        return userMapper.mapToUserDtoList(userRepository.findAll());
    }

    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionType.USER_NOT_FOUND, id.toString()));
    }

    public User createUser(UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        if (userRepository.findByPesel(user.getPesel()) != null) {
            throw new EntityAlreadyExistsException(ExceptionType.USER_NOT_CHANGE, user.getPesel());
        }
        return userRepository.save(user);
    }

    public User updateUser(UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        return userRepository.save(user);

    }

    public void deleteUser(Long id) {
        getUser(id);
        userRepository.deleteById(id);
    }

    public List<UserAssignmentDto> getUserAssignments(Long userId) {
        return userRepository.findById(userId)
                .map(User::getAssignments)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionType.USER_NOT_FOUND, userId.toString()))
                .stream()
                .map(UserAssignmentMapper::mapToUserAssignmentDto)
                .collect(Collectors.toList());
    }

}
