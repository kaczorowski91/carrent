package pl.kaczorowski.carrent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kaczorowski.carrent.dto.AssignmentDto;
import pl.kaczorowski.carrent.mapper.AssignmentMapper;
import pl.kaczorowski.carrent.service.AssignmentService;

import java.util.List;

@RestController
@RequestMapping("/v1/assignment")
public class AssignmentController {

    @Autowired
    private AssignmentMapper assignmentMapper;

    @Autowired
    private AssignmentService assignmentService;

    @GetMapping
    public List<AssignmentDto> getAssigments() {
        return assignmentMapper.mapToAssignmentDtoList(assignmentService.getAssignments());
    }
}
