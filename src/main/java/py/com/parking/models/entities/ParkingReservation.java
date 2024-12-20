package py.com.parking.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "parking_reservations")
public class ParkingReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "parking_area_id", nullable = false)
    private ParkingArea parkingArea;

    @ManyToOne
    @JoinColumn(name = "special_event_id")
    private SpecialEvent specialEvent;

    @Column(name = "reservation_date", nullable = false)
    private LocalDate reservationDate;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Pattern(regexp = "PENDING|CONFIRMED|CANCELLED", message = "Status must be one of 'PENDING', 'CONFIRMED', or 'CANCELLED'.")
    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "is_special_event", nullable = false)
    private Boolean isSpecialEvent;

    @NotNull
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @NotNull
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
