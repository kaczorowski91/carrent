package pl.kaczorowski.carrent.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentDto {
    private Long id;
    private LocalDateTime begin;
    private LocalDateTime appointedEnd;
    private LocalDateTime realEnd;
    private Long userId;
    private Long vehicleId;
    private double plannedCost;
    private double realCost;

    public AssignmentDto(LocalDateTime begin, LocalDateTime appointedEnd, LocalDateTime realEnd, Long userId, Long vehicleId) {
        this.begin = begin;
        this.appointedEnd = appointedEnd;
        this.realEnd = realEnd;
        this.userId = userId;
        this.vehicleId = vehicleId;
    }
}
