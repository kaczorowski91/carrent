package pl.kaczorowski.carrent.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserAssignmentDto {
    private Long id;
    private LocalDateTime begin;
    private LocalDateTime appointedEnd;
    private LocalDateTime realEnd;
    private Long vehicleId;
    private String vehicleName;
    private String vehicleIdentifier;

}
