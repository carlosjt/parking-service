package py.com.parking.rest.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import py.com.parking.models.dto.SpecialEventDTO;
import py.com.parking.services.SpecialEventService;

import java.util.List;

@Path("/special-events")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SpecialEventResource {

    @Inject
    SpecialEventService specialEventService;

    @GET
    public List<SpecialEventDTO> getAllSpecialEvents() {
        return specialEventService.getAllSpecialEvents();
    }
}
