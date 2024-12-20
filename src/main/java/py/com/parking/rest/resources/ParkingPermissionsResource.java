package py.com.parking.rest.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import py.com.parking.services.ParkingPermissionsService;

@Path("/parking-permissions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ParkingPermissionsResource {

    @Inject
    ParkingPermissionsService service;

    @GET
    public Response getParkingPermissions() {
        return Response.ok(service.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response getParkingPermissions(@PathParam("id") Integer id) {
        return Response.ok(service.findById(id)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteParkingPermissions(@PathParam("id") Integer id) {
        if(service.findById(id).isPresent()) {
            service.deleteById(id);
            return Response.ok().build();
        }else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
