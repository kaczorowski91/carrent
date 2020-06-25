package pl.kaczorowski.carrent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kaczorowski.carrent.dto.UserAssignmentDto;
import pl.kaczorowski.carrent.entity.User;
import pl.kaczorowski.carrent.exception.EntityAlreadyExistsException;
import pl.kaczorowski.carrent.exception.EntityNotFoundException;
import pl.kaczorowski.carrent.exception.ExceptionType;
import pl.kaczorowski.carrent.mapper.UserAssignmentMapper;
import pl.kaczorowski.carrent.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAssignmentMapper userAssignmentMapper;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionType.USER_NOT_FOUND, id.toString()));
    }

    public User createUser(User user) {
        if (userRepository.findByPesel(user.getPesel()) != null) {
            throw new EntityAlreadyExistsException(ExceptionType.USER_NOT_CHANGE, user.getPesel());
        }
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        User presentUser = getUser(user.getId());
        if (!presentUser.getPesel().equals(user.getPesel())) {
            throw new EntityAlreadyExistsException(ExceptionType.USER_NOT_CHANGE, user.getPesel());
        }
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
