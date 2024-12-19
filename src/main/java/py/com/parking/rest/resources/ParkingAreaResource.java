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

    /**
     * Endpoint para obtener todas las áreas de estacionamiento con filtros opcionales.
     *
     * @param available Opcional. Si es true, filtra solo áreas con espacios disponibles.
     * @param occupied  Opcional. Si es true, filtra solo áreas completamente ocupadas.
     * @return Lista de áreas de estacionamiento filtrada según los parámetros.
     */
    @GET
    public List<ParkingAreaDTO> getAllParkingAreas(
            @QueryParam("available") Boolean available,
            @QueryParam("occupied") Boolean occupied
    ) {
        return parkingAreaService.getAllParkingAreasWithAvailability(available, occupied);
    }

    /**
     * Endpoint para crear un área de estacionamiento.
     *
     * @param parkingAreaDTO Datos del área de estacionamiento.
     * @return Área de estacionamiento creada.
     */
    @POST
    public Response createParkingArea(ParkingAreaDTO parkingAreaDTO) {
        ParkingAreaDTO createdParkingArea = parkingAreaService.createParkingArea(parkingAreaDTO);
        return Response.status(Response.Status.CREATED).entity(createdParkingArea).build();
    }

    /**
     * Endpoint para actualizar un área de estacionamiento.
     *
     * @param id             ID del área de estacionamiento.
     * @param parkingAreaDTO Datos actualizados del área de estacionamiento.
     * @return Área de estacionamiento actualizada.
     */
    @PUT
    @Path("/{id}")
    public Response updateParkingArea(@PathParam("id") Integer id, ParkingAreaDTO parkingAreaDTO) {
        ParkingAreaDTO updatedParkingArea = parkingAreaService.updateParkingArea(id, parkingAreaDTO);
        return Response.ok(updatedParkingArea).build();
    }

    /**
     * Endpoint para eliminar un área de estacionamiento.
     *
     * @param id ID del área de estacionamiento a eliminar.
     * @return Respuesta con el estado de la operación.
     */
    @DELETE
    @Path("/{id}")
    public Response deleteParkingArea(@PathParam("id") Integer id) {
        parkingAreaService.deleteParkingArea(id);
        return Response.noContent().build();
    }

    /**
     * Endpoint para actualizar la ocupación de un área de estacionamiento.
     *
     * @param id             ID del área de estacionamiento.
     * @param occupiedSpaces Nuevo número de espacios ocupados.
     * @return Área de estacionamiento actualizada.
     */
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
