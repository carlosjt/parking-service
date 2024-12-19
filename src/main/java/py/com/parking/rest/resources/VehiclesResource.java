package py.com.parking.rest.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import py.com.parking.models.entities.Vehicles;
import py.com.parking.services.VehiclesService;

@Path("/vehicles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VehiclesResource {

    @Autowired
    VehiclesService vehiclesService;

    @POST
    public Response addVehicle(Vehicles vehicle) {
        return Response.ok(vehiclesService.save(vehicle)).build();
    }

    @GET
    public Response getAllVehicles() {
        return Response.ok(vehiclesService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response getVehicleById(@PathParam("id") Long id) {
        return Response.ok(vehiclesService.findById(id)).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateVehicle(@PathParam(value = "id") Long id, Vehicles vehicle) {
        if(vehicle.getId() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("User ID must match the path parameter and cannot be null.")
                    .build();
        }
        return vehiclesService.findById(id)
                .map(dbVehicle -> Response.ok(vehiclesService.save(vehicle)).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }
}
