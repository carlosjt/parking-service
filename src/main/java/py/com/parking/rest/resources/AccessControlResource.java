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

    /**
     * Endpoint para registrar la entrada de un vehículo.
     *
     * @param licensePlate Matrícula del vehículo.
     * @return Registro de acceso creado.
     */
    @POST
    @Path("/entry")
    public Response registerEntry(
            @QueryParam("deviceType") String deviceType,
            @QueryParam("licensePlate") String licensePlate
    ) {
        if (deviceType == null || deviceType.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Device Type registration is required").build();
        }
        if (licensePlate == null || licensePlate.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("License plate is required").build();
        }
        return Response.ok(accessControlService.registerEntry(deviceType, licensePlate)).build();
    }

    /**
     * Endpoint para registrar la salida de un vehículo.
     *
     * @param licensePlate Matrícula del vehículo.
     * @return Registro de acceso actualizado.
     */
    @POST
    @Path("/exit")
    public Response registerExit(
            @QueryParam("licensePlate") String licensePlate
    ) {
        if (licensePlate == null || licensePlate.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("License plate is required").build();
        }
        return Response.ok(accessControlService.registerExit(licensePlate)).build();
    }

    /**
     * Endpoint para obtener la ocupación actual del estacionamiento.
     *
     * @return Número de vehículos actualmente dentro del estacionamiento.
     */
    @GET
    @Path("/occupancy")
    public Response getCurrentOccupancy() {
        long occupancy = accessControlService.getCurrentOccupancy();
        return Response.ok(occupancy).build();
    }
}
