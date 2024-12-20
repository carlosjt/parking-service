package py.com.parking.rest.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import py.com.parking.models.dto.ParkingAreaDTO;
import py.com.parking.services.ParkingAreaService;

import java.util.List;

@Path("/parking-areas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ParkingAreaResource {

    @Inject
    ParkingAreaService parkingAreaService;

    @GET
    public List<ParkingAreaDTO> getAllParkingAreas(
            @QueryParam("available") Boolean available,
            @QueryParam("occupied") Boolean occupied
    ) {
        return parkingAreaService.getAllParkingAreasWithAvailability(available, occupied);
    }

    @POST
    public Response createParkingArea(ParkingAreaDTO parkingAreaDTO) {
        ParkingAreaDTO createdParkingArea = parkingAreaService.createParkingArea(parkingAreaDTO);
        return Response.status(Response.Status.CREATED).entity(createdParkingArea).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateParkingArea(@PathParam("id") Integer id, ParkingAreaDTO parkingAreaDTO) {
        ParkingAreaDTO updatedParkingArea = parkingAreaService.updateParkingArea(id, parkingAreaDTO);
        return Response.ok(updatedParkingArea).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteParkingArea(@PathParam("id") Integer id) {
        parkingAreaService.deleteParkingArea(id);
        return Response.noContent().build();
    }

    @PUT
    @Path("/{id}/occupancy")
    public Response updateParkingAreaOccupancy(@PathParam("id") Integer id, @QueryParam("occupiedSpaces") Integer occupiedSpaces) {
        if (occupiedSpaces == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("occupiedSpaces is required").build();
        }

        ParkingAreaDTO updatedParkingArea = parkingAreaService.updateParkingAreaOccupancy(id, occupiedSpaces);
        return Response.ok(updatedParkingArea).build();
    }
}
