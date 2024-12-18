package py.com.parking.business;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;

@ApplicationPath(Api.PATH_SERVICE)
@OpenAPIDefinition(
        info = @Info(
                version = "1.0.0",
                title = "PS API",
                description = "API for Parking Service",
                license = @License(name = "Simple License")
        )
)
public class ApplicationResource extends Application {}
