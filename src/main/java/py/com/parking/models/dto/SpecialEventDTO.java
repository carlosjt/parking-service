package py.com.parking.models.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@Builder
public class SpecialEventDTO {

    private Integer id;

    @NotBlank(message = "Event name is required.")
    private String name;

    private String description;

    @NotNull(message = "Event date is required.")
    @Future(message = "Event date must be in the future.")
    private LocalDate eventDate;

    @NotNull(message = "Start time is required.")
    private LocalTime startTime;

    @NotNull(message = "End time is required.")
    private LocalTime endTime;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
