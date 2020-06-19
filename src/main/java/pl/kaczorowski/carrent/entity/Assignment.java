package pl.kaczorowski.carrent.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime begin;
    private LocalDateTime appointedEnd;
    private LocalDateTime realEnd;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    public Assignment(LocalDateTime begin, LocalDateTime appointedEnd, LocalDateTime realEnd, User user, Vehicle vehicle) {
        this.begin = begin;
        this.appointedEnd = appointedEnd;
        this.realEnd = realEnd;
        this.user = user;
        this.vehicle = vehicle;
    }
}
