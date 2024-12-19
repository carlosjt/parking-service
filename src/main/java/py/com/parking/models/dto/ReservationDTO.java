package py.com.parking.models.dto;

import io.vertx.ext.auth.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
@Getter
@Setter
public class ReservationDTO {

    private Integer id;
    private UsersDTO user;
    private ParkingAreaDTO parkingArea;
    private LocalDate reservationDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String status;
    private boolean isSpecialEvent;
}