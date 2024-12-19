package py.com.parking.rest.resources;

import py.com.parking.models.dto.ReservationDTO;
import py.com.parking.services.ReservationService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/reservations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReservationResource {

    @Inject
    private ReservationService reservationService;

    @GET
    public Response getAllReservations() {
        List<ReservationDTO> reservations = reservationService.getAllReservations();
        return Response.ok(reservations).build();
    }

    @GET
    @Path("/{id}")
    public Response getReservationById(@PathParam("id") Integer id) {
        return reservationService.getReservationById(id)
                .map(reservation -> Response.ok(reservation).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    public Response createReservation(ReservationDTO reservationDTO) {
        ReservationDTO created = reservationService.createReservation(reservationDTO);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateReservation(@PathParam("id") Integer id, ReservationDTO reservationDTO) {
        try {
            ReservationDTO updated = reservationService.updateReservation(id, reservationDTO);
            return Response.ok(updated).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteReservation(@PathParam("id") Integer id) {
        reservationService.deleteReservation(id);
        return Response.noContent().build();
    }
}
