package py.com.parking.models.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ParkingAreaDTO {

    private Integer id;

    @NotBlank
    private String name;

    @NotNull
    @Min(1)
    private Integer totalSpaces;

    @NotNull
    @Min(0)
    private Integer occupiedSpaces;

    @NotBlank
    private String location;

    @NotNull
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
