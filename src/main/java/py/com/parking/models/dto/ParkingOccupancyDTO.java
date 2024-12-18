package py.com.parking.models.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ParkingOccupancyDTO {

    private Integer id;

    @NotNull
    private ParkingAreaDTO parkingArea;

    private VehiclesDTO vehicle;

    @NotNull
    private LocalDateTime entryTime;

    private LocalDateTime exitTime;

    @NotNull
    private Boolean isOccupied;

    @NotNull
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
