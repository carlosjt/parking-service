package py.com.parking.rest.resources;

import py.com.parking.models.dto.ParkingAreaDTO;
import py.com.parking.models.dto.ParkingReservationDTO;
import py.com.parking.models.dto.SpecialEventDTO;
import py.com.parking.models.dto.UsersDTO;
import py.com.parking.services.ParkingReservationService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Path("/parking-reservations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ParkingReservationResource {

    @Inject
    ParkingReservationService parkingReservationService;

    @GET
    public Response getAllReservations() {
        List<ParkingReservationDTO> reservations = parkingReservationService.getAllReservations();
        return Response.ok(reservations).build();
    }

    @GET
    @Path("/{id}")
    public Response getReservationById(@PathParam("id") Integer id) {
        return Response.ok(parkingReservationService.getReservationById(id)).build();
    }

    @POST
    public Response createReservation(
            @QueryParam("userId") Integer userId,
            @QueryParam("parkingAreaId") Integer parkingAreaId,
            @QueryParam("specialEventId") Integer specialEventId,
            @QueryParam("reservationDate") String reservationDate,
            @QueryParam("startTime") String startTime,
            @QueryParam("endTime") String endTime) {
        if (userId == null) {
            throw new BadRequestException("The 'userId' parameter is required.");
        }

        if (parkingAreaId == null) {
            throw new BadRequestException("The 'parkingAreaId' parameter is required.");
        }

        if (reservationDate == null) {
            throw new BadRequestException("The 'reservationDate' parameter is required.");
        }

        if (startTime == null) {
            throw new BadRequestException("The 'startTime' parameter is required.");
        }

        if (endTime == null) {
            throw new BadRequestException("The 'endTime' parameter is required.");
        }
        final ParkingReservationDTO request = ParkingReservationDTO.builder()
                .user(UsersDTO.builder().id(userId).build())
                .parkingArea(ParkingAreaDTO.builder().id(parkingAreaId).build())
                .specialEvent(specialEventId != null ? SpecialEventDTO.builder().id(specialEventId).build() : null)
                .reservationDate(parseLocalDate(reservationDate, "reservationDate"))
                .startTime(parseLocalTime(startTime, "startTime"))
                .endTime(parseLocalTime(endTime, "endTime"))
                .status("PENDING")
                .isSpecialEvent(specialEventId != null)
                .build();
        return Response.status(Response.Status.CREATED).entity(parkingReservationService.createReservation(request)).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateReservation(@PathParam("id") Integer id, ParkingReservationDTO parkingReservationDTO) {
        try {
            ParkingReservationDTO updated = parkingReservationService.updateReservation(id, parkingReservationDTO);
            return Response.ok(updated).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteReservation(@PathParam("id") Integer id) {
        parkingReservationService.deleteReservation(id);
        return Response.noContent().build();
    }

    private static LocalDate parseLocalDate(String value, String paramName) {
        try {
            return LocalDate.parse(value);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid format for parameter '" + paramName + "'. Expected format is YYYY-MM-DD.");
        }
    }

    private static LocalTime parseLocalTime(String value, String paramName) {
        try {
            return LocalTime.parse(value);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid format for parameter '" + paramName + "'. Expected format is HH:mm.");
        }
    }
}
