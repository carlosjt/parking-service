package py.com.parking.models.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@Builder
public class ParkingReservationDTO {

    @Positive(message = "ID must be a positive number.")
    private Integer id;

    private UsersDTO user;

    private ParkingAreaDTO parkingArea;

    private SpecialEventDTO specialEvent;

    @NotNull(message = "Reservation date is required.")
    @FutureOrPresent(message = "Reservation date must be today or in the future.")
    private LocalDate reservationDate;

    @NotNull(message = "Start time is required.")
    private LocalTime startTime;

    @NotNull(message = "End time is required.")
    private LocalTime endTime;

    @NotNull(message = "Status is required.")
    @Pattern(regexp = "PENDING|CONFIRMED|CANCELLED", message = "Status must be one of 'PENDING', 'CONFIRMED', or 'CANCELLED'.")
    private String status;

    private Boolean isSpecialEvent;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
