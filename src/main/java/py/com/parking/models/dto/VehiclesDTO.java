package py.com.parking.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VehiclesDTO {

    private Integer id;

    @NotNull
    private UsersDTO user;

    @NotBlank
    private String licensePlate;

    @NotBlank
    private String vehicleType;

    private String brand;

    private String model;

    private String color;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
