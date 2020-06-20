package pl.kaczorowski.carrent.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleAssignmentDto {
    private Long id;
    private LocalDateTime begin;
    private LocalDateTime appointedEnd;
    private LocalDateTime realEnd;
    private Long userId;
    private String userFirstName;
    private String userLastName;
    private double plannedCost;
    private double realCost;

}
