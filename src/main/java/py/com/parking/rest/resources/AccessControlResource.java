package py.com.parking.rest.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import py.com.parking.services.AccessControlService;

@Path("/access-control")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccessControlResource {

    @Inject
    AccessControlService accessControlService;

    @POST
    @Path("/entry")
    public Response registerEntry(
            @QueryParam("parkingAreaId") Integer parkingAreaId,
            @QueryParam("deviceType") String deviceType,
            @QueryParam("deviceIdentityValue") String deviceIdentityValue
    ) {
        if (parkingAreaId == null || parkingAreaId <= 0) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Parking Area is required").build();
        }
        if (deviceType == null || deviceType.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Device Type registration is required").build();
        }
        if (deviceIdentityValue == null || deviceIdentityValue.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Device Identity value is required").build();
        }
        return Response.ok(accessControlService.registerEntry(parkingAreaId, deviceType, deviceIdentityValue)).build();
    }

    @PUT
    @Path("/exit")
    public Response registerExit(
            @QueryParam("parkingAreaId") Integer parkingAreaId,
            @QueryParam("deviceType") String deviceType,
            @QueryParam("deviceIdentityValue") String deviceIdentityValue    ) {
        if (parkingAreaId == null || parkingAreaId <= 0) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Parking Area is required").build();
        }
        if (deviceType == null || deviceType.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Device Type registration is required").build();
        }
        if (deviceIdentityValue == null || deviceIdentityValue.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Device Identity value is required").build();
        }
        return Response.ok(accessControlService.registerExit(parkingAreaId, deviceType, deviceIdentityValue)).build();
    }

    @GET
    @Path("/occupancy")
    public Response getCurrentOccupancy() {
        return Response.ok(accessControlService.getCurrentOccupancy()).build();
    }
}
