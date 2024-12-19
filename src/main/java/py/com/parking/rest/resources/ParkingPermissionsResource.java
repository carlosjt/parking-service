package py.com.parking.rest.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import py.com.parking.services.ParkingPermissionsService;

@Path("/parking_permissions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ParkingPermissionsResource {

    @Autowired
    ParkingPermissionsService service;

    @GET
    public Response getParkingPermissions() {
        return Response.ok(service.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response getParkingPermissions(@PathParam("id") Long id) {
        return Response.ok(service.findById(id)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteParkingPermissions(@PathParam("id") Long id) {
        if(service.findById(id).isPresent()) {
            service.deleteById(id);
            return Response.ok().build();
        }else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
