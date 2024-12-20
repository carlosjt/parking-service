package py.com.parking.rest.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import py.com.parking.models.dto.ParkingOccupancyDTO;
import py.com.parking.services.ParkingOccupancyService;

import java.util.List;

@Path("/parking-occupancy")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ParkingOccupancyResource {

    @Inject
    ParkingOccupancyService parkingOccupancyService;

    @GET
    public List<ParkingOccupancyDTO> getAllParkingOccupancies() {
        return parkingOccupancyService.getAllOccupancies();
    }

    @GET
    @Path("/filter")
    public List<ParkingOccupancyDTO> getOccupanciesByParkingArea(
            @QueryParam("parkingAreaId") Integer parkingAreaId) {
        if (parkingAreaId == null) {
            throw new BadRequestException("Parking area ID is required.");
        }
        return parkingOccupancyService.getOccupanciesByParkingArea(parkingAreaId);
    }
}
