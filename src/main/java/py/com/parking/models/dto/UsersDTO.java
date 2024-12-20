package py.com.parking.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class UsersDTO {

    private Integer id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    @NotBlank
    private String email;

    @Pattern(regexp = "^[0-9+\\-() ]*$", message = "Invalid phone number format")
    private String phone;

    @NotBlank
    private String userType;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
