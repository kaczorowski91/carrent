package pl.kaczorowski.carrent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.kaczorowski.carrent.dto.AssignmentDto;
import pl.kaczorowski.carrent.mapper.AssignmentMapper;
import pl.kaczorowski.carrent.service.AssignmentService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1/assignment")
public class AssignmentController {

    @Autowired
    private AssignmentMapper assignmentMapper;

    @Autowired
    private AssignmentService assignmentService;

    @GetMapping
    public List<AssignmentDto> getAssignments() {
        return assignmentMapper.mapToAssignmentDtoList(assignmentService.getAssignments());
    }

    @GetMapping("/{id}")
    public AssignmentDto getAssignment(@PathVariable Long id) {
        return assignmentMapper.mapToAssignmentDto(assignmentService.getAssignment(id));
    }

    @PostMapping (consumes = MediaType.APPLICATION_JSON_VALUE)
    public AssignmentDto saveAssignment(@RequestBody AssignmentDto assignmentDto) {
            return assignmentMapper.mapToAssignmentDto(assignmentService.saveAssignment(assignmentDto));

    }

    @DeleteMapping("/{id}")
    public void deleteAssignment(@PathVariable Long id) {
        assignmentService.deleteAssignment(id);
    }

    @PutMapping("/{id}/end")
    public LocalDateTime updateAssigmentWithEndDate(@PathVariable Long id) {
       return assignmentService.endDateUpateAssignment(id);
    }

    @PutMapping
    public AssignmentDto updateAssignment(@RequestBody AssignmentDto assignmentDto) {
        return assignmentMapper.mapToAssignmentDto(assignmentService.updateAssignment(assignmentMapper.mapToAssignment(assignmentDto)));
    }
}
