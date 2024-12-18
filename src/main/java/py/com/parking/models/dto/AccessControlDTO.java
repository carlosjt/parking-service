package py.com.parking.models.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AccessControlDTO {

    private Integer id;

    @NotNull
    private VehiclesDTO vehicle;

    @NotNull
    private LocalDateTime entryTime;

    private LocalDateTime exitTime;

    @NotNull
    private Boolean isAuthorized;

    @NotNull
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
