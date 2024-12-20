package py.com.parking.rest.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import py.com.parking.models.entities.Users;
import py.com.parking.services.UserService;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserService userService;

    @GET
    public Response getAllUsers() {
        return Response.ok(userService.findAllUsers()).build();
    }

    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") Integer id) {
        return userService.findUserById(id)
                .map(user -> Response.ok(user).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    public Response createUser(Users user) {
        return Response.ok(userService.saveUserWithVehicles(user)).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateUser(@PathParam("id") Integer id, Users user) {
        if (user.getId() == null || !user.getId().equals(id)) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("User ID must match the path parameter and cannot be null.")
                    .build();
        }
        return userService.findUserById(id)
                .map(dbUser -> Response.ok(userService.saveUserWithVehicles(user)).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") Integer id) {
        if (userService.findUserById(id).isPresent()) {
            userService.deleteUserById(id);
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
