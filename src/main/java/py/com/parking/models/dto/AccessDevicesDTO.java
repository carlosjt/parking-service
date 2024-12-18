package py.com.parking.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AccessDevicesDTO {

    private Long id;

    @NotBlank
    private String deviceType;

    @NotBlank
    private String location;

    @NotNull
    private Boolean isActive;

    @NotNull
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
